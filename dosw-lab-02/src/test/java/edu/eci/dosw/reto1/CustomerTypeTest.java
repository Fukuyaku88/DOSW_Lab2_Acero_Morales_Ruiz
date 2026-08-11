package edu.eci.dosw.reto1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTypeTest {

    @Test
    void newCustomerShouldHaveFivePercentDiscount() {
        assertEquals(5, CustomerType.NEW.getDiscount());
    }

    @Test
    void frecuentCustomerShouldHaveTenPercentDiscount() {
        assertEquals(10, CustomerType.FRECUENT.getDiscount());
    }
}
