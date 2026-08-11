package edu.eci.dosw.reto4;

import java.math.BigDecimal;

/**
 * ROL EN BRIDGE: Abstraction.
 *
 * Provee el control de alto nivel para convertir divisas, delegando
 * en un RateProvider (Implementation) la obtención de la tasa real.
 * Esta clase NUNCA sabe de dónde vienen las tasas ni cómo se calculan;
 * solo conoce el contrato declarado en RateProvider.
 *
 * Gracias a esta separación, se puede cambiar la fuente de tasas
 * (memoria, archivo, API externa) sin modificar esta clase, y se
 * pueden crear variantes de conversión (Refined Abstractions) sin
 * tocar las implementaciones concretas de RateProvider.
 */
public abstract class CurrencyExchanger {

    protected final RateProvider rateProvider;

    protected CurrencyExchanger(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    /**
     * Convierte un monto de una moneda origen a una moneda destino,
     * usando la tasa específica para ese par (nunca una tasa fija
     * compartida entre todos los pares).
     */
    public abstract ConversionResult convert(BigDecimal amount, Currency from, Currency to);
}
