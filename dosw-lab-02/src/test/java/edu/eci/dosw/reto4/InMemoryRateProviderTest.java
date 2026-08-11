package edu.eci.dosw.reto4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryRateProviderTest {

    private InMemoryRateProvider provider;

    @BeforeEach
    void setUp() {
        provider = new InMemoryRateProvider();
    }

    @Test
    void shouldReturnOneWhenConvertingSameCurrency() {
        assertEquals(BigDecimal.ONE, provider.getRate(Currency.USD, Currency.USD));
        assertEquals(BigDecimal.ONE, provider.getRate(Currency.EUR, Currency.EUR));
        assertEquals(BigDecimal.ONE, provider.getRate(Currency.JPY, Currency.JPY));
        assertEquals(BigDecimal.ONE, provider.getRate(Currency.COP, Currency.COP));
    }

    @Test
    void shouldReturnDefaultRateForKnownPairs() {
        assertEquals(new BigDecimal("0.92"), provider.getRate(Currency.USD, Currency.EUR));
        assertEquals(new BigDecimal("155.30"), provider.getRate(Currency.USD, Currency.JPY));
        assertEquals(new BigDecimal("4050.00"), provider.getRate(Currency.USD, Currency.COP));
        assertEquals(new BigDecimal("0.000247"), provider.getRate(Currency.COP, Currency.USD));
    }

    @Test
    void rateShouldNotBeSymmetricByDefault() {
        // USD->EUR y EUR->USD son tasas independientes, no el inverso matematico exacto
        assertEquals(new BigDecimal("0.92"), provider.getRate(Currency.USD, Currency.EUR));
        assertEquals(new BigDecimal("1.09"), provider.getRate(Currency.EUR, Currency.USD));
    }

    @Test
    void addRateShouldOverwriteAnExistingRate() {
        provider.addRate(Currency.USD, Currency.EUR, new BigDecimal("1.50"));

        assertEquals(new BigDecimal("1.50"), provider.getRate(Currency.USD, Currency.EUR));
    }

    @Test
    void addRateShouldBeIgnoredWhenFromEqualsTo() {
        // getRate ya corta antes por from==to, asi que el resultado sigue siendo
        // BigDecimal.ONE sin importar el valor que se intente registrar.
        provider.addRate(Currency.USD, Currency.USD, new BigDecimal("99"));

        assertEquals(BigDecimal.ONE, provider.getRate(Currency.USD, Currency.USD));
    }
}
