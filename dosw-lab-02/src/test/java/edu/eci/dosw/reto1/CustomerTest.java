package edu.eci.dosw.reto1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        Inventory.getInstanceInventory().addProduct("CustomerTest-Book", 30000.0);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldThrowWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer(null, CustomerType.NEW));
    }

    @Test
    void shouldThrowWhenNameIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("   ", CustomerType.NEW));
    }

    @Test
    void shouldCreateCustomerWithValidName() {
        Customer customer = new Customer("CustomerTest-John", CustomerType.NEW);

        assertNotNull(customer.getShoppingCart());
    }

    @Test
    void shouldReturnDiscountAccordingToCustomerType() {
        Customer newCustomer = new Customer("CustomerTest-New", CustomerType.NEW);
        Customer frecuentCustomer = new Customer("CustomerTest-Freq", CustomerType.FRECUENT);

        assertEquals(5, newCustomer.getDiscount());
        assertEquals(10, frecuentCustomer.getDiscount());
    }

    @Test
    void addProductShouldDelegateToShoppingCart() {
        Customer customer = new Customer("CustomerTest-Delegate", CustomerType.NEW);

        customer.addProduct("CustomerTest-Book", 2);

        assertEquals(60000.0, customer.getShoppingCart().calculateSubTotal());
    }

    @Test
    void getShoppingCartShouldAlwaysReturnSameInstance() {
        Customer customer = new Customer("CustomerTest-SameCart", CustomerType.NEW);

        assertSame(customer.getShoppingCart(), customer.getShoppingCart());
    }

    @Test
    void printLastReceiptShouldThrowWhenNoReceiptsYet() {
        Customer customer = new Customer("CustomerTest-NoReceipt", CustomerType.NEW);

        assertThrows(NoSuchElementException.class, customer::printLastReceipt);
    }

    @Test
    void payShouldGenerateAReceiptAndClearTheCart() {
        Customer customer = new Customer("CustomerTest-Pay", CustomerType.NEW);
        customer.addProduct("CustomerTest-Book", 1);

        customer.pay();

        assertEquals(0.0, customer.getShoppingCart().calculateSubTotal());
    }

    @Test
    void printLastReceiptShouldPrintAfterPaying() {
        Customer customer = new Customer("CustomerTest-Print", CustomerType.NEW);
        customer.addProduct("CustomerTest-Book", 1);
        customer.pay();

        ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));

        customer.printLastReceipt();

        String output = outputCapture.toString();
        assertTrue(output.contains("DON PEPES'S STORE"));
        assertTrue(output.contains("CustomerTest-Book"));
    }

    @Test
    void printLastReceiptShouldReflectMostRecentPurchaseOnly() {
        Customer customer = new Customer("CustomerTest-TwicePay", CustomerType.NEW);

        customer.addProduct("CustomerTest-Book", 1);
        customer.pay(); // first receipt: total item price 30000.0

        customer.addProduct("CustomerTest-Book", 3);
        customer.pay(); // second (last) receipt: total item price 90000.0

        ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));

        customer.printLastReceipt();

        String output = outputCapture.toString();
        assertTrue(output.contains("COP 90000.0"));
    }
}
