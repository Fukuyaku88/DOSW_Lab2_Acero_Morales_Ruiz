package edu.eci.dosw.reto5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void newOrderShouldBeEmpty() {
        assertTrue(order.getOrders().isEmpty());
        assertEquals(0, order.getOrderPrice());
    }

    @Test
    void shouldAddBeverageToOrder() {
        Beverage coffee = new Coffee("Espresso", 5000);
        order.addBeverage(coffee);

        assertEquals(1, order.getOrders().size());
        assertTrue(order.getOrders().contains(coffee));
    }

    @Test
    void shouldCalculateTotalPriceForMultipleBeverages() {
        Beverage coffee1 = new Coffee("Espresso", 5000);
        coffee1 = new Topping(coffee1, "Milk", 1000);

        Beverage coffee2 = new Coffee("Cappuccino", 8000);
        coffee2 = new Topping(coffee2, "Whipped Cream", 2000);

        order.addBeverage(coffee1);
        order.addBeverage(coffee2);

        assertEquals(16000, order.getOrderPrice());
    }

    @Test
    void shouldPreserveInsertionOrder() {
        Beverage coffee1 = new Coffee("Espresso", 5000);
        Beverage coffee2 = new Coffee("Latte", 4000);

        order.addBeverage(coffee1);
        order.addBeverage(coffee2);

        assertEquals(coffee1, order.getOrders().get(0));
        assertEquals(coffee2, order.getOrders().get(1));
    }
}
