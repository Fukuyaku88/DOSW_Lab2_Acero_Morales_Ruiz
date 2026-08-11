package edu.eci.dosw.reto5;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomizedCoffeC5Test {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputCapture;

    @BeforeEach
    void setUp() {
        outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldPrintBothOrdersWithDecoratedDescriptions() {
        CustomizedCoffeC5.run();

        String output = outputCapture.toString();

        assertTrue(output.contains("Expresso + Milk + Chocolate + Caramel"));
        assertTrue(output.contains("Cappuccino + Whipped Cream + Mint + Condense Milk"));
    }

    @Test
    void shouldPrintCorrectTotalPrice() {
        CustomizedCoffeC5.run();

        String output = outputCapture.toString();

        // coffee1: 5000+1000+1500+1200 = 8700
        // coffee2: 8000+2000+1300+3500 = 14800
        // total: 23500
        assertTrue(output.contains("Total Price: 23500"));
    }
}
