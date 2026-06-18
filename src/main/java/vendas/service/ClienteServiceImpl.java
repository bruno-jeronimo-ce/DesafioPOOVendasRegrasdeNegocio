package vendas.service;

import lombok.RequiredArgsConstructor;
import vendas.exception.RecursoNaoEncontradoException;
import vendas.model.Cliente;
import vendas.repository.ClienteRepository;
import vendas.service.interfaces.IClienteService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente salvar(Cliente cliente) {

        cliente.setDataCadastro(LocalDateTime.now());

        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Cliente não encontrado"));
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {

        Cliente clienteExistente = buscarPorId(id);

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setCpf(cliente.getCpf());
        clienteExistente.setTelefone(cliente.getTelefone());

        return clienteRepository.save(clienteExistente);
    }

    @Override
    public void remover(Long id) {

        Cliente cliente = buscarPorId(id);

        clienteRepository.delete(cliente);
    }
}