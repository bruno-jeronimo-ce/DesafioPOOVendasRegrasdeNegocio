package vendas.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculadoraVendaPadrao implements CalculadoraVenda {

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor;
    }
}