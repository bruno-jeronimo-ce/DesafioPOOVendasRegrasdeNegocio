package vendas.service;

import lombok.RequiredArgsConstructor;
import vendas.exception.RecursoNaoEncontradoException;
import vendas.model.Estoque;
import vendas.repository.EstoqueRepository;
import vendas.service.interfaces.IEstoqueService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueServiceImpl implements IEstoqueService {

    private final EstoqueRepository estoqueRepository;

    @Override
    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    @Override
    public Estoque buscarPorProduto(Long produtoId) {

        return estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Estoque não encontrado"));
    }

    @Override
    public Estoque atualizarEstoque(Long produtoId, Estoque estoque) {

        Estoque estoqueExistente = buscarPorProduto(produtoId);

        estoqueExistente.setQuantidade(
                estoque.getQuantidade()
        );

        return estoqueRepository.save(estoqueExistente);
    }
}