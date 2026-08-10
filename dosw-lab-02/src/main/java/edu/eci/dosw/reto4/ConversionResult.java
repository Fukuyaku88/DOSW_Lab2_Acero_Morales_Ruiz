package edu.eci.dosw.reto4;

import java.math.BigDecimal;

/**
 * Representa el resultado de convertir un monto de una moneda origen
 * a una moneda destino. Contiene toda la información que el enunciado
 * pide mostrar: monto original, moneda origen, monto convertido y
 * moneda destino.
 */
public class ConversionResult {

    private final BigDecimal originalAmount;
    private final Currency sourceCurrency;
    private final BigDecimal convertedAmount;
    private final Currency targetCurrency;

    public ConversionResult(BigDecimal originalAmount, Currency sourceCurrency,
                             BigDecimal convertedAmount, Currency targetCurrency) {
        this.originalAmount = originalAmount;
        this.sourceCurrency = sourceCurrency;
        this.convertedAmount = convertedAmount;
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    @Override
    public String toString() {
        return String.format("%s %s -> %s %s",
                originalAmount.stripTrailingZeros().toPlainString(), sourceCurrency,
                convertedAmount.setScale(2, java.math.RoundingMode.HALF_UP), targetCurrency);
    }
}
