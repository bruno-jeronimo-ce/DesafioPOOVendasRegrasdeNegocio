package vendas.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ItemVendaResponse {

    private Long produtoId;

    private String produto;

    private Integer quantidade;

    private BigDecimal precoUnitario;
}