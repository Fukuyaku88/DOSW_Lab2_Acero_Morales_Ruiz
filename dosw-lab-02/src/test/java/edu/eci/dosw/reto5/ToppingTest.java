package edu.eci.dosw.reto5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToppingTest {

    @Test
    void shouldAppendNameToDescription() {
        Beverage base = new Coffee("Espresso", 5000);
        Beverage withMilk = new Topping(base, "Milk", 1000);

        assertEquals("Espresso + Milk", withMilk.getDescription());
    }

    @Test
    void shouldAddOwnPriceToBasePrice() {
        Beverage base = new Coffee("Espresso", 5000);
        Beverage withMilk = new Topping(base, "Milk", 1000);

        assertEquals(6000, withMilk.getPrice());
    }

    @Test
    void shouldStackMultipleToppings() {
        Beverage coffee = new Coffee("Espresso", 5000);
        coffee = new Topping(coffee, "Milk", 1000);
        coffee = new Topping(coffee, "Chocolate", 1500);
        coffee = new Topping(coffee, "Caramel", 1200);

        assertEquals("Espresso + Milk + Chocolate + Caramel", coffee.getDescription());
        assertEquals(8700, coffee.getPrice());
    }

    @Test
    void toppingWithZeroPriceShouldNotChangeTotal() {
        Beverage base = new Coffee("Espresso", 5000);
        Beverage decorated = new Topping(base, "Extra Napkin", 0);

        assertEquals(5000, decorated.getPrice());
    }
}
