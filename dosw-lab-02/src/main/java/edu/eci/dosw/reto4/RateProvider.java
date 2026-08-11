package edu.eci.dosw.reto4;

import java.math.BigDecimal;

/**
 * ROL EN BRIDGE: Implementation.
 *
 * Declara el contrato común que deben cumplir todas las fuentes
 * de tasas de cambio (Concrete Implementations), sin importar
 * de dónde provengan los datos (memoria, archivo, API externa, etc).
 *
 * La Abstraction (CurrencyExchanger) solo conoce esta interfaz,
 * nunca los detalles concretos de cómo se calculan o almacenan
 * las tasas.
 */
public interface RateProvider {

    /**
     * Obtiene la tasa de cambio específica para convertir de una
     * moneda origen a una moneda destino.
     *
     * @param from moneda origen
     * @param to   moneda destino
     * @return tasa de cambio (multiplicador) para ese par específico
     * @throws IllegalArgumentException si no existe una tasa registrada
     *                                   para el par solicitado
     */
    BigDecimal getRate(Currency from, Currency to);
}
