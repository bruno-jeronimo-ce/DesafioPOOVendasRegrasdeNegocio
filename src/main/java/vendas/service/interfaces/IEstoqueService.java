package vendas.service.interfaces;

import java.util.List;

import vendas.model.Estoque;

public interface IEstoqueService {

    List<Estoque> listarTodos();

    Estoque buscarPorProduto(Long produtoId);

    Estoque atualizarEstoque(Long produtoId, Estoque estoque);
}