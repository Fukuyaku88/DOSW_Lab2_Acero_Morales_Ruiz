package edu.eci.dosw.reto4;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ROL EN BRIDGE: Refined Abstraction.
 *
 * Variante de CurrencyExchanger que agrega la capacidad de convertir
 * un mismo monto origen hacia VARIAS monedas destino en una sola
 * operación, cumpliendo el requisito:
 * "Allow one source amount to be converted into one or more
 * destination currencies."
 *
 * Sigue trabajando únicamente a través de la interfaz RateProvider,
 * por lo que funciona igual sin importar qué Concrete Implementation
 * se le inyecte.
 */
public class MultiTargetExchanger extends CurrencyExchanger {

    public MultiTargetExchanger(RateProvider rateProvider) {
        super(rateProvider);
    }

    @Override
    public ConversionResult convert(BigDecimal amount, Currency from, Currency to) {
        BigDecimal rate = rateProvider.getRate(from, to);
        BigDecimal converted = amount.multiply(rate);
        return new ConversionResult(amount, from, converted, to);
    }

    /**
     * Convierte un monto origen hacia una lista de monedas destino.
     */
    public List<ConversionResult> convertToMultiple(BigDecimal amount, Currency from,
                                                      List<Currency> targets) {
        return targets.stream()
                .map(target -> convert(amount, from, target))
                .collect(Collectors.toList());
    }
}
