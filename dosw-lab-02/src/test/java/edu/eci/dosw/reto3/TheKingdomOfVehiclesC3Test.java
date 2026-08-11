package edu.eci.dosw.reto3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TheKingdomOfVehiclesC3Test {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private String runWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture, true, StandardCharsets.UTF_8));

        TheKingdomOfVehiclesC3.run();

        return outputCapture.toString(StandardCharsets.UTF_8);
    }

    @Test
    void shouldPrintWelcomeMessageAndExitImmediately() {
        String output = runWithInput("EXIT\n");

        assertTrue(output.contains("Hola bienvenido a The Kingdom Of Vehicles"));
    }

    @Test
    void shouldAddLandVehicleSuccessfully() {
        String output = runWithInput("LAND ECONOMY CAR\nEXIT\n");

        assertTrue(output.contains("El vehiculo ECONOMY CAR fue agregado correctamente"));
    }

    @Test
    void shouldRejectInputWithWrongNumberOfTokens() {
        String output = runWithInput("LAND ECONOMY\nEXIT\n");

        assertTrue(output.contains("Ingrese los 3 datos solicitados correctamente"));
    }

    @Test
    void shouldRejectInvalidEnumValues() {
        String output = runWithInput("SPACE ECONOMY CAR\nEXIT\n");

        assertTrue(output.contains("no coincide con los valores disponibles"));
    }

    @Test
    void shouldReportEmptyCartWhenPayingWithoutVehicles() {
        String output = runWithInput("PAY\nEXIT\n");

        assertTrue(output.contains("El carrito esta vacio, no hay recibo que imprimir"));
    }

    @Test
    void shouldPrintReceiptWithoutDiscountForFewerThanFourVehicles() {
        String output = runWithInput("LAND ECONOMY CAR\nPAY\nEXIT\n");

        // Se formatea con el mismo patron que usa la clase productiva,
        // para que el test no dependa del locale del sistema.
        String expectedSubtotal = String.format("%.2f", 80999.9);

        assertTrue(output.contains("THE KINGDOM OF VEHICLES - RECEIPT"));
        assertTrue(output.contains("SUBTOTAL: " + expectedSubtotal));
        assertTrue(output.contains("DISCOUNT: 0.0%"));
        assertTrue(output.contains("FINAL TOTAL: " + expectedSubtotal));
    }

    @Test
    void shouldApplyDiscountWhenBuyingFourOrMoreVehicles() {
        String input = "LAND ECONOMY CAR\n"
                + "WATER ECONOMY MOTORBOAT\n"
                + "AIR ECONOMY AIRPLANE\n"
                + "LAND USED MOTORCYCLE\n"
                + "PAY\n"
                + "EXIT\n";

        String output = runWithInput(input);

        assertTrue(output.contains("DISCOUNT: 10% cause the purchase of 4 or more units in our shop"));
    }

    @Test
    void shouldClearShoppingCart() {
        String output = runWithInput("LAND ECONOMY CAR\nCLEAR\nPAY\nEXIT\n");

        assertTrue(output.contains("El carrito fue vaciado correctamente"));
        assertTrue(output.contains("El carrito esta vacio, no hay recibo que imprimir"));
    }
}
