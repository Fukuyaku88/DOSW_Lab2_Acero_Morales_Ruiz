package edu.eci.dosw.reto4;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversionResultTest {

    @Test
    void gettersShouldReturnConstructorValues() {
        ConversionResult result = new ConversionResult(
                new BigDecimal("100"), Currency.USD,
                new BigDecimal("92"), Currency.EUR);

        assertEquals(new BigDecimal("100"), result.getOriginalAmount());
        assertEquals(Currency.USD, result.getSourceCurrency());
        assertEquals(new BigDecimal("92"), result.getConvertedAmount());
        assertEquals(Currency.EUR, result.getTargetCurrency());
    }

    @Test
    void toStringShouldStripTrailingZerosFromOriginalAmount() {
        ConversionResult result = new ConversionResult(
                new BigDecimal("150.500"), Currency.USD,
                new BigDecimal("200.456"), Currency.EUR);

        assertEquals("150.5 USD -> 200.46 EUR", result.toString());
    }

    @Test
    void toStringShouldRoundConvertedAmountToTwoDecimalsHalfUp() {
        ConversionResult result = new ConversionResult(
                new BigDecimal("10"), Currency.JPY,
                new BigDecimal("1.005"), Currency.COP);

        assertEquals("10 JPY -> 1.01 COP", result.toString());
    }
}
