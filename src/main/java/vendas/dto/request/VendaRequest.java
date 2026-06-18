package vendas.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VendaRequest {

    private Long clienteId;

    private List<ItemVendaRequest> itens;
}