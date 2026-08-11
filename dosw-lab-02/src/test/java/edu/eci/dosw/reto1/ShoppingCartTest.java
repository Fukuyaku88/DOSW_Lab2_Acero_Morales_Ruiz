package edu.eci.dosw.reto1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        Inventory inventory = Inventory.getInstanceInventory();
        inventory.addProduct("ShoppingCartTest-Shirt", 20000.0);
        inventory.addProduct("ShoppingCartTest-Pants", 50000.0);

        Customer customer = new Customer("ShoppingCartTest-Customer", CustomerType.FRECUENT);
        cart = customer.getShoppingCart();
    }

    @Test
    void shouldAddRegisteredProductToCart() {
        cart.addProduct("ShoppingCartTest-Shirt", 2);

        assertEquals(40000.0, cart.calculateSubTotal());
    }

    @Test
    void shouldThrowWhenAddingUnregisteredProduct() {
        assertThrows(IllegalArgumentException.class,
                () -> cart.addProduct("ShoppingCartTest-NotRegistered", 1));
    }

    @Test
    void shouldCalculateSubTotalForMultipleProducts() {
        cart.addProduct("ShoppingCartTest-Shirt", 2);
        cart.addProduct("ShoppingCartTest-Pants", 1);

        assertEquals(90000.0, cart.calculateSubTotal());
    }

    @Test
    void shouldCalculateDiscountBasedOnCustomerType() {
        cart.addProduct("ShoppingCartTest-Shirt", 1); // 20000, FRECUENT = 10%

        assertEquals(2000.0, cart.calculateDiscount());
    }

    @Test
    void shouldCalculateTotalAsSubtotalMinusDiscount() {
        cart.addProduct("ShoppingCartTest-Shirt", 1); // 20000 - 2000 discount

        assertEquals(18000.0, cart.calculateTotal());
    }

    @Test
    void generateReceiptShouldReturnANonNullReceipt() {
        cart.addProduct("ShoppingCartTest-Shirt", 2);

        Receipt receipt = cart.generateReceipt();

        assertNotNull(receipt);
    }

    @Test
    void generateReceiptShouldClearTheCart() {
        cart.addProduct("ShoppingCartTest-Shirt", 1);

        cart.generateReceipt();

        assertEquals(0.0, cart.calculateSubTotal());
    }

    @Test
    void emptyCartShouldHaveZeroSubtotalDiscountAndTotal() {
        assertEquals(0.0, cart.calculateSubTotal());
        assertEquals(0.0, cart.calculateDiscount());
        assertEquals(0.0, cart.calculateTotal());
    }
}
