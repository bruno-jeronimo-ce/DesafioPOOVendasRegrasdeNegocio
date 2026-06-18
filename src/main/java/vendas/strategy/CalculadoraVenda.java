package vendas.strategy;

import java.math.BigDecimal;

public interface CalculadoraVenda {

    BigDecimal calcular(BigDecimal valor);
}