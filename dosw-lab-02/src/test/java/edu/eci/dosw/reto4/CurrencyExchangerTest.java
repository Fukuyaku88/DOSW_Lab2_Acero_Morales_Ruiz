package edu.eci.dosw.reto4;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyExchangerTest {

    @Test
    void shouldExposeTheInjectedRateProviderToSubclasses() {
        RateProvider fixedRateProvider = (from, to) -> BigDecimal.TEN;

        CurrencyExchanger exchanger = new CurrencyExchanger(fixedRateProvider) {
            @Override
            public ConversionResult convert(BigDecimal amount, Currency from, Currency to) {
                BigDecimal rate = rateProvider.getRate(from, to);
                return new ConversionResult(amount, from, amount.multiply(rate), to);
            }
        };

        ConversionResult result = exchanger.convert(new BigDecimal("5"), Currency.USD, Currency.EUR);

        assertEquals(new BigDecimal("50"), result.getConvertedAmount());
    }
}
