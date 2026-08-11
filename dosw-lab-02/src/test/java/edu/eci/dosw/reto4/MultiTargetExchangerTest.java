package edu.eci.dosw.reto4;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiTargetExchangerTest {

    @Test
    void convertShouldMultiplyAmountByProviderRate() {
        RateProvider fixedRateProvider = (from, to) -> new BigDecimal("2");
        MultiTargetExchanger exchanger = new MultiTargetExchanger(fixedRateProvider);

        ConversionResult result = exchanger.convert(new BigDecimal("10"), Currency.USD, Currency.EUR);

        assertEquals(new BigDecimal("20"), result.getConvertedAmount());
        assertEquals(Currency.USD, result.getSourceCurrency());
        assertEquals(Currency.EUR, result.getTargetCurrency());
        assertEquals(new BigDecimal("10"), result.getOriginalAmount());
    }

    @Test
    void convertShouldPropagateExceptionFromRateProvider() {
        RateProvider failingProvider = (from, to) -> {
            throw new IllegalArgumentException("No rate for that pair");
        };
        MultiTargetExchanger exchanger = new MultiTargetExchanger(failingProvider);

        assertThrows(IllegalArgumentException.class,
                () -> exchanger.convert(new BigDecimal("10"), Currency.USD, Currency.EUR));
    }

    @Test
    void convertToMultipleShouldReturnOneResultPerTarget() {
        RateProvider fixedRateProvider = (from, to) -> new BigDecimal("2");
        MultiTargetExchanger exchanger = new MultiTargetExchanger(fixedRateProvider);

        List<ConversionResult> results = exchanger.convertToMultiple(
                new BigDecimal("10"), Currency.USD, List.of(Currency.EUR, Currency.JPY, Currency.COP));

        assertEquals(3, results.size());
        assertTrue(results.stream().anyMatch(r -> r.getTargetCurrency() == Currency.EUR));
        assertTrue(results.stream().anyMatch(r -> r.getTargetCurrency() == Currency.JPY));
        assertTrue(results.stream().anyMatch(r -> r.getTargetCurrency() == Currency.COP));
    }

    @Test
    void convertToMultipleShouldReturnEmptyListForEmptyTargets() {
        RateProvider fixedRateProvider = (from, to) -> BigDecimal.ONE;
        MultiTargetExchanger exchanger = new MultiTargetExchanger(fixedRateProvider);

        List<ConversionResult> results = exchanger.convertToMultiple(
                new BigDecimal("10"), Currency.USD, List.of());

        assertTrue(results.isEmpty());
    }

    @Test
    void convertToMultipleShouldUseRealRatesFromInMemoryProvider() {
        MultiTargetExchanger exchanger = new MultiTargetExchanger(new InMemoryRateProvider());

        List<ConversionResult> results = exchanger.convertToMultiple(
                new BigDecimal("100"), Currency.USD, List.of(Currency.EUR));

        assertEquals(new BigDecimal("92.00"), results.get(0).getConvertedAmount());
    }
}
