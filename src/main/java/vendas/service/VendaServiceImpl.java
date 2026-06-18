package vendas.service;

import lombok.RequiredArgsConstructor;
import vendas.dto.request.VendaRequest;
import vendas.dto.response.VendaResponse;
import vendas.exception.EstoqueInsuficienteException;
import vendas.exception.RecursoNaoEncontradoException;
import vendas.model.*;
import vendas.repository.ClienteRepository;
import vendas.repository.EstoqueRepository;
import vendas.repository.ProdutoRepository;
import vendas.repository.VendaRepository;
import vendas.service.interfaces.IVendaService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaServiceImpl implements IVendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    @Override
    public VendaResponse registrarVenda(VendaRequest request) {

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Cliente não encontrado"));

        BigDecimal total = BigDecimal.ZERO;

        for (var item : request.getItens()) {

            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() ->
                            new RecursoNaoEncontradoException("Produto não encontrado"));

            Estoque estoque = estoqueRepository.findByProdutoId(produto.getId())
                    .orElseThrow(() ->
                            new RecursoNaoEncontradoException("Estoque não encontrado"));

            if (estoque.getQuantidade() < item.getQuantidade()) {
                throw new EstoqueInsuficienteException(
                        "Produto " + produto.getNome() +
                        " possui apenas " + estoque.getQuantidade() +
                        " unidade(s) em estoque."
                );
            }

            estoque.setQuantidade(
                    estoque.getQuantidade() - item.getQuantidade()
            );

            estoqueRepository.save(estoque);

            total = total.add(
                    produto.getPreco().multiply(
                            BigDecimal.valueOf(item.getQuantidade())
                    )
            );
        }

        Venda venda = new Venda();

        venda.setCliente(cliente);
        venda.setValorTotal(total);
        venda.setDataVenda(LocalDateTime.now());

        Venda vendaSalva = vendaRepository.save(venda);

        return VendaResponse.builder()
                .id(vendaSalva.getId())
                .clienteId(cliente.getId())
                .valorTotal(vendaSalva.getValorTotal())
                .dataVenda(vendaSalva.getDataVenda())
                .build();
    }

    @Override
    public List<VendaResponse> listarTodas() {

        return vendaRepository.findAll()
                .stream()
                .map(venda -> VendaResponse.builder()
                        .id(venda.getId())
                        .clienteId(venda.getCliente().getId())
                        .valorTotal(venda.getValorTotal())
                        .dataVenda(venda.getDataVenda())
                        .build())
                .toList();
    }

    @Override
    public VendaResponse buscarPorId(Long id) {

        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Venda não encontrada"));

        return VendaResponse.builder()
                .id(venda.getId())
                .clienteId(venda.getCliente().getId())
                .valorTotal(venda.getValorTotal())
                .dataVenda(venda.getDataVenda())
                .build();
    }

    @Override
    public List<VendaResponse> buscarPorCliente(Long clienteId) {

        return vendaRepository.findByClienteId(clienteId)
                .stream()
                .map(venda -> VendaResponse.builder()
                        .id(venda.getId())
                        .clienteId(venda.getCliente().getId())
                        .valorTotal(venda.getValorTotal())
                        .dataVenda(venda.getDataVenda())
                        .build())
                .toList();
    }
}