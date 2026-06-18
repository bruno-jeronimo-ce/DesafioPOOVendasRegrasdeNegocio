package vendas.service.interfaces;

import java.util.List;

import vendas.model.Cliente;

public interface IClienteService {

    Cliente salvar(Cliente cliente);

    List<Cliente> listarTodos();

    Cliente buscarPorId(Long id);

    Cliente atualizar(Long id, Cliente cliente);

    void remover(Long id);
}