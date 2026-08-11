package edu.eci.dosw.reto5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CoffeeTest {

    @Test
    void shouldReturnCorrectDescription() {
        Coffee coffee = new Coffee("Espresso", 5000);
        assertEquals("Espresso", coffee.getDescription());
    }

    @Test
    void shouldReturnCorrectPrice() {
        Coffee coffee = new Coffee("Espresso", 5000);
        assertEquals(5000, coffee.getPrice());
    }

    @Test
    void shouldHandleZeroPrice() {
        Coffee coffee = new Coffee("Free Sample", 0);
        assertEquals(0, coffee.getPrice());
    }

    @Test
    void differentInstancesShouldBeIndependent() {
        Coffee c1 = new Coffee("Latte", 4000);
        Coffee c2 = new Coffee("Mocha", 4500);

        assertNotEquals(c1.getPrice(), c2.getPrice());
        assertNotEquals(c1.getDescription(), c2.getDescription());
    }
}
