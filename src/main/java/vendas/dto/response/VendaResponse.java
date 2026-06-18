package vendas.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class VendaResponse {

    private Long id;

    private Long clienteId;

    private BigDecimal valorTotal;

    private LocalDateTime dataVenda;

    private List<ItemVendaResponse> itens;
}