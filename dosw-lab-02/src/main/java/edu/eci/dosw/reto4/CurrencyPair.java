package edu.eci.dosw.reto4;

import java.util.Objects;

/**
 * Representa un par de monedas (origen -> destino).
 * Se usa como clave para almacenar la tasa de cambio específica
 * de ese par, evitando así el problema original: una sola tasa
 * compartida para todas las conversiones.
 */
public final class CurrencyPair {

    private final Currency from;
    private final Currency to;

    public CurrencyPair(Currency from, Currency to) {
        this.from = from;
        this.to = to;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyPair that = (CurrencyPair) o;
        return from == that.from && to == that.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }


    @Override
    public String toString() {
        return from + "->" + to;
    }
}
