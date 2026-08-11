package edu.eci.dosw.reto1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DonPepesStoreC1Test {

    private final PrintStream originalOut = System.out;

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldPrintReceiptWithExpectedItemsAndTotals() {
        ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));

        DonPepesStoreC1.run();

        String output = outputCapture.toString();

        // Products
        assertTrue(output.contains("T-shirt"));
        assertTrue(output.contains("Pants"));
        assertTrue(output.contains("Cookies"));
        assertTrue(output.contains("Natural Juice"));

        // Subtotal: (20000*2) + (500*3) + (3000*5) + (50000*1) = 106500.0
        assertTrue(output.contains("COP 106500.0"));
        // Discount: FRECUENT (10%) of 106500.0 = 10650.0
        assertTrue(output.contains("COP 10650.0"));
        // Final total: 106500.0 - 10650.0 = 95850.0
        assertTrue(output.contains("COP 95850.0"));
    }
}
