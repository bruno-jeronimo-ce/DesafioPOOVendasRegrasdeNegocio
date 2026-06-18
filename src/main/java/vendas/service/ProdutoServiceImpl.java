package vendas.service;

import lombok.RequiredArgsConstructor;
import vendas.exception.RecursoNaoEncontradoException;
import vendas.model.Produto;
import vendas.repository.ProdutoRepository;
import vendas.service.interfaces.IProdutoService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements IProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(Long id) {

        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Produto não encontrado"));
    }

    @Override
    public Produto atualizar(Long id, Produto produto) {

        Produto produtoExistente = buscarPorId(id);

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setCategoria(produto.getCategoria());

        return produtoRepository.save(produtoExistente);
    }

    @Override
    public void remover(Long id) {

        Produto produto = buscarPorId(id);

        produtoRepository.delete(produto);
    }
}