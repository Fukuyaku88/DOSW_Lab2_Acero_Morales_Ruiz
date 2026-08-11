package edu.eci.dosw.reto4;

/**
 * Monedas soportadas por el servicio de conversión.
 * Se puede extender agregando nuevas constantes sin tocar
 * la lógica de conversión (Abstraction) ni el contrato de
 * obtención de tasas (Implementation).
 */
public enum Currency {
    USD,
    EUR,
    JPY,
    COP
}
