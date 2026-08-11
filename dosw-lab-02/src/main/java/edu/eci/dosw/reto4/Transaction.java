package edu.eci.dosw.reto4;

import java.math.BigDecimal;
import java.util.List;

/**
 * Representa una transacción completa realizada por el usuario:
 * un monto en una moneda origen, convertido hacia una o más
 * monedas destino.
 */
public class Transaction {

    private final BigDecimal amount;
    private final Currency sourceCurrency;
    private final List<ConversionResult> results;

    public Transaction(BigDecimal amount, Currency sourceCurrency, List<ConversionResult> results) {
        this.amount = amount;
        this.sourceCurrency = sourceCurrency;
        this.results = results;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public List<ConversionResult> getResults() {
        return results;
    }
}
