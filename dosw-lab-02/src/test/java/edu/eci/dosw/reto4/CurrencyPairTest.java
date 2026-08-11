package edu.eci.dosw.reto4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CurrencyPairTest {

    @Test
    void pairsWithSameFromAndToShouldBeEqual() {
        CurrencyPair pair1 = new CurrencyPair(Currency.USD, Currency.EUR);
        CurrencyPair pair2 = new CurrencyPair(Currency.USD, Currency.EUR);

        assertEquals(pair1, pair2);
        assertEquals(pair1.hashCode(), pair2.hashCode());
    }

    @Test
    void pairsWithSwappedCurrenciesShouldNotBeEqual() {
        CurrencyPair pair1 = new CurrencyPair(Currency.USD, Currency.EUR);
        CurrencyPair pair2 = new CurrencyPair(Currency.EUR, Currency.USD);

        assertNotEquals(pair1, pair2);
    }

    @Test
    void shouldNotBeEqualToNullOrDifferentType() {
        CurrencyPair pair = new CurrencyPair(Currency.USD, Currency.EUR);

        assertNotEquals(null, pair);
        assertNotEquals("USD->EUR", pair);
    }

    @Test
    void gettersShouldReturnConstructorValues() {
        CurrencyPair pair = new CurrencyPair(Currency.JPY, Currency.COP);

        assertEquals(Currency.JPY, pair.getFrom());
        assertEquals(Currency.COP, pair.getTo());
    }

    @Test
    void toStringShouldShowArrowNotation() {
        CurrencyPair pair = new CurrencyPair(Currency.USD, Currency.EUR);

        assertEquals("USD->EUR", pair.toString());
    }
}
