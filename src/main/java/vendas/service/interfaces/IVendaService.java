package vendas.service.interfaces;

import java.util.List;

import vendas.dto.request.VendaRequest;
import vendas.dto.response.VendaResponse;

public interface IVendaService {

    VendaResponse registrarVenda(VendaRequest request);

    List<VendaResponse> listarTodas();

    VendaResponse buscarPorId(Long id);

    List<VendaResponse> buscarPorCliente(Long clienteId);
}