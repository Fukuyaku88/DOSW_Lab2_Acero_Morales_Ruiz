package edu.eci.dosw.reto1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReceiptTest {

    @Test
    void printStringShouldContainAllProductRows() {
        Product product1 = new Product("ReceiptTest-Notebook", 5000.0);
        Product product2 = new Product("ReceiptTest-Pen", 1000.0);

        CartItem item1 = new CartItem(product1, 2);
        CartItem item2 = new CartItem(product2, 5);

        Receipt receipt = new Receipt(List.of(item1, item2), 15000.0, 1500.0, 13500.0);

        String output = receipt.printString();

        assertTrue(output.contains("ReceiptTest-Notebook"));
        assertTrue(output.contains("ReceiptTest-Pen"));
    }

    @Test
    void printStringShouldContainSubtotalDiscountAndTotal() {
        Product product = new Product("ReceiptTest-Book", 5000.0);
        CartItem item = new CartItem(product, 1);

        Receipt receipt = new Receipt(List.of(item), 15000.0, 1500.0, 13500.0);

        String output = receipt.printString();

        assertTrue(output.contains("COP 15000.0"));
        assertTrue(output.contains("COP 1500.0"));
        assertTrue(output.contains("COP 13500.0"));
    }

    @Test
    void printStringShouldContainHeaderAndFooterMessages() {
        Receipt receipt = new Receipt(List.of(), 0.0, 0.0, 0.0);

        String output = receipt.printString();

        assertTrue(output.contains("DON PEPES'S STORE"));
        assertTrue(output.contains("SUMMARY"));
        assertTrue(output.contains("Thank you for trusting us"));
    }

    @Test
    void printStringShouldHandleEmptyPurchaseList() {
        Receipt receipt = new Receipt(List.of(), 0.0, 0.0, 0.0);

        String output = receipt.printString();

        assertTrue(output.contains("COP 0.0"));
    }
}
