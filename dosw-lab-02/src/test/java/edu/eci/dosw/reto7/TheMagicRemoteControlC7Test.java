package edu.eci.dosw.reto7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TheMagicRemoteControlC7Test {

    private final PrintStream originalOut = System.out;

    @AfterEach
    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void shouldRunWithoutThrowingExceptions() {
        ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));

        assertDoesNotThrow(TheMagicRemoteControlC7::run);
    }

    @Test
    void shouldPrintFullScenarioIncludingHistorySummary() {
        ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));

        TheMagicRemoteControlC7.run();

        String output = capturedOut.toString();
        assertTrue(output.contains("Actions"));
        assertTrue(output.contains("Volume: 25"));
        assertTrue(output.contains("Volume: 45"));
        assertTrue(output.contains("Volume: 99"));
        assertTrue(output.contains("=== SUMMARY DETAILS ==="));
        assertTrue(output.contains("Total History Records:"));
    }
}
