package edu.eci.dosw.reto4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TheCurrencyExchangeScamC4Test {

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

        TheCurrencyExchangeScamC4.run();

        return outputCapture.toString(StandardCharsets.UTF_8);
    }

    @Test
    void shouldRejectInvalidAmountThenAcceptValidOne() {
        String input = "abc\n-5\n50\nUSD\nEUR\nn\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Invalid amount, please try again."));
        assertTrue(output.contains("The amount must be greater than zero."));
        assertTrue(output.contains("--- Transaction result ---"));
    }

    @Test
    void shouldRejectInvalidSourceCurrencyThenAcceptValidOne() {
        String input = "50\nXXX\nUSD\nEUR\nn\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Unsupported currency. Options:"));
        assertTrue(output.contains("--- Transaction result ---"));
    }

    @Test
    void shouldRejectTargetCurrencySameAsSourceThenAcceptValidOne() {
        String input = "50\nUSD\nUSD\nEUR\nn\n";

        String output = runWithInput(input);

        assertTrue(output.contains("The target currency cannot be the same as the source currency: USD"));
        assertTrue(output.contains("--- Transaction result ---"));
    }

    @Test
    void shouldRejectInvalidTargetCurrencyThenAcceptValidOne() {
        String input = "50\nUSD\nXYZ\nEUR\nn\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Invalid target currency: XYZ"));
        assertTrue(output.contains("--- Transaction result ---"));
    }

    @Test
    void shouldConvertToMultipleTargetsInOneTransaction() {
        String input = "100\nUSD\nEUR,JPY\nn\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Original amount: 100 USD"));
        assertTrue(output.contains("Converted amount: 92.00 EUR"));
        assertTrue(output.contains("Converted amount: 15530.00 JPY"));
        assertTrue(output.contains("=== Accumulated totals by target currency ==="));
        assertTrue(output.contains("EUR: 92.00"));
        assertTrue(output.contains("JPY: 15530.00"));
    }

    @Test
    void shouldAccumulateTotalsAcrossMultipleTransactionsForSameTargetCurrency() {
        // Tx1: 100 USD -> EUR (rate 0.92) = 92.00
        // Tx2: 1000 JPY -> EUR (rate 0.0059) = 5.90
        // Total esperado en EUR: 97.90
        String input = "100\nUSD\nEUR\ny\n1000\nJPY\nEUR\nn\n";

        String output = runWithInput(input);

        assertTrue(output.contains("=== Accumulated totals by target currency ==="));
        assertTrue(output.contains("EUR: 97.90"));
    }
}
