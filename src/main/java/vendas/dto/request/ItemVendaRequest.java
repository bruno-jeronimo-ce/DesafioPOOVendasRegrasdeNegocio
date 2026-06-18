package vendas.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVendaRequest {

    private Long produtoId;

    private Integer quantidade;
}