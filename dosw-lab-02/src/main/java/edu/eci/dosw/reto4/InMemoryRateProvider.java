package edu.eci.dosw.reto4;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * ROL EN BRIDGE: Concrete Implementation.
 *
 * Representa cómo se SUPLEN las tasas de cambio en esta solución:
 * cada par de monedas (CurrencyPair) tiene su PROPIA tasa, almacenada
 * en un Map<CurrencyPair, BigDecimal>. Esto es justamente lo que
 * corrige el "scam" del dueño anterior, que aplicaba una sola tasa
 * a todas las conversiones.
 *
 * Si en el futuro las tasas debieran venir de un archivo, una API
 * externa o una base de datos, bastaría con crear otra clase que
 * implemente RateProvider (por ejemplo FileRateProvider o
 * ApiRateProvider) sin modificar en absoluto la Abstraction.
 */
public class InMemoryRateProvider implements RateProvider {

    private final Map<CurrencyPair, BigDecimal> rates;

    public InMemoryRateProvider() {
        this.rates = new HashMap<>();
        loadDefaultRates();
    }

    /**
     * Carga las tasas de cambio reales, una por cada par de monedas.
     * Las tasas son aproximadas y solo con fines ilustrativos.
     */
    private void loadDefaultRates() {
        // Base: 1 USD
        addRate(Currency.USD, Currency.EUR, new BigDecimal("0.92"));
        addRate(Currency.USD, Currency.JPY, new BigDecimal("155.30"));
        addRate(Currency.USD, Currency.COP, new BigDecimal("4050.00"));

        addRate(Currency.EUR, Currency.USD, new BigDecimal("1.09"));
        addRate(Currency.EUR, Currency.JPY, new BigDecimal("168.80"));
        addRate(Currency.EUR, Currency.COP, new BigDecimal("4405.00"));

        addRate(Currency.JPY, Currency.USD, new BigDecimal("0.0064"));
        addRate(Currency.JPY, Currency.EUR, new BigDecimal("0.0059"));
        addRate(Currency.JPY, Currency.COP, new BigDecimal("26.10"));

        addRate(Currency.COP, Currency.USD, new BigDecimal("0.000247"));
        addRate(Currency.COP, Currency.EUR, new BigDecimal("0.000227"));
        addRate(Currency.COP, Currency.JPY, new BigDecimal("0.0383"));
    }

    /**
     * Permite registrar o actualizar la tasa de un par específico.
     * Útil para pruebas o para cargar tasas desde otra fuente externa.
     */
    public void addRate(Currency from, Currency to, BigDecimal rate) {
        if (from == to) {
            return;
        }
        rates.put(new CurrencyPair(from, to), rate);
    }

    @Override
    public BigDecimal getRate(Currency from, Currency to) {
        if (from == to) {
            return BigDecimal.ONE;
        }
        CurrencyPair pair = new CurrencyPair(from, to);
        BigDecimal rate = rates.get(pair);
        if (rate == null) {
            throw new IllegalArgumentException(
                    "No exchange rate registered for pair: " + pair);
        }
        return rate;
    }
}
