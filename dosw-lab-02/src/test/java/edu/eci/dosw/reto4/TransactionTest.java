package edu.eci.dosw.reto4;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    @Test
    void shouldExposeAmountSourceCurrencyAndResults() {
        ConversionResult result = new ConversionResult(
                new BigDecimal("100"), Currency.USD,
                new BigDecimal("92"), Currency.EUR);

        Transaction transaction = new Transaction(new BigDecimal("100"), Currency.USD, List.of(result));

        assertEquals(new BigDecimal("100"), transaction.getAmount());
        assertEquals(Currency.USD, transaction.getSourceCurrency());
        assertEquals(1, transaction.getResults().size());
        assertEquals(result, transaction.getResults().get(0));
    }
}
