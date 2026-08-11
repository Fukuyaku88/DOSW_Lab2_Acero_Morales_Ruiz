package edu.eci.dosw.reto1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartItemTest {

    @Test
    void shouldCalculateTotalPriceAsUnitPriceTimesQuantity() {
        Product product = new Product("CartItemTest-Soda", 2500.0);
        CartItem item = new CartItem(product, 3);

        assertEquals(7500.0, item.getTotalPrice());
    }

    @Test
    void shouldExposeProductAndQuantity() {
        Product product = new Product("CartItemTest-Chips", 4000.0);
        CartItem item = new CartItem(product, 2);

        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQ());
    }
}
