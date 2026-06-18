package vendas.service.interfaces;

import java.util.List;

import vendas.model.Produto;

public interface IProdutoService {

    Produto salvar(Produto produto);

    List<Produto> listarTodos();

    Produto buscarPorId(Long id);

    Produto atualizar(Long id, Produto produto);

    void remover(Long id);
}