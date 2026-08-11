package edu.eci.dosw.reto1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class InventoryTest {

    private final Inventory inventory = Inventory.getInstanceInventory();

    @Test
    void getInstanceInventoryShouldAlwaysReturnSameInstance() {
        Inventory other = Inventory.getInstanceInventory();

        assertSame(inventory, other);
    }

    @Test
    void shouldRegisterAndRetrieveAProduct() {
        inventory.addProduct("InventoryTest-Notebook", 15000.0);

        Product product = Inventory.getProduct("InventoryTest-Notebook");

        assertNotNull(product);
        assertEquals("InventoryTest-Notebook", product.name());
        assertEquals(15000.0, product.unitPrice());
    }

    @Test
    void shouldReturnNullForUnregisteredProduct() {
        Product product = Inventory.getProduct("InventoryTest-DoesNotExist");

        assertNull(product);
    }

    @Test
    void addingSameNameShouldOverwritePreviousProduct() {
        inventory.addProduct("InventoryTest-Pen", 1000.0);
        inventory.addProduct("InventoryTest-Pen", 1500.0);

        Product product = Inventory.getProduct("InventoryTest-Pen");

        assertEquals(1500.0, product.unitPrice());
    }
}
