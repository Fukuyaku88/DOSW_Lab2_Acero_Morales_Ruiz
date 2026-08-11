package edu.eci.dosw.reto2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for the console flow of TheFiveStarChefC2.
 *
 * IMPORTANT: TheFiveStarChefC2 keeps a single {@code static final Scanner}
 * bound to {@code System.in} at class-loading time. To be able to control
 * every keystroke the program "reads", we redirect {@code System.in} to a
 * single combined script BEFORE the class is ever touched, and then drive
 * the whole scripted conversation across ordered test methods that consume
 * that shared stream in sequence (run() -> run() -> main()).
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("TheFiveStarChefC2 (console flow)")
class TheFiveStarChefC2Test {

    private static final InputStream ORIGINAL_IN = System.in;
    private static final PrintStream ORIGINAL_OUT = System.out;

    // run() #1: invalid bread option, chicken, invalid+valid cheese, invalid+two vegetables, no sauce, one other ingredient
    private static final String RUN_1_SCRIPT = "9 1 2 5 1 9 1 2 0 0 1 0 ";
    // run() #2: sesame bread, invalid meat option, skip cheese, no vegetables, one sauce, no other ingredient
    private static final String RUN_2_SCRIPT = "2 5 1 0 0 1 0 0 ";
    // main() delegates to run(): pick first option everywhere, skip/finish every optional & multiple step
    private static final String MAIN_SCRIPT = "1 1 0 0 0 0";

    @BeforeAll
    static void redirectSystemInBeforeFirstClassUse() {
        String fullScript = RUN_1_SCRIPT + RUN_2_SCRIPT + MAIN_SCRIPT;
        System.setIn(new ByteArrayInputStream(fullScript.getBytes(StandardCharsets.UTF_8)));
    }

    @AfterAll
    static void restoreOriginalSystemStreams() {
        System.setIn(ORIGINAL_IN);
        System.setOut(ORIGINAL_OUT);
    }

    private String runAndCaptureOutput(Runnable action) {
        ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOutput, true, StandardCharsets.UTF_8));

        action.run();

        System.setOut(ORIGINAL_OUT);
        return capturedOutput.toString(StandardCharsets.UTF_8);
    }

    @Test
    @Order(1)
    @DisplayName("should reject invalid options in required, optional and multiple-selection steps and still finish the order")
    void shouldCompleteFullCustomizedRunWithInvalidOptionRecovery() {
        String output = runAndCaptureOutput(TheFiveStarChefC2::run);

        long invalidOptionOccurrences = output.lines()
                .filter(line -> line.contains("Invalid option. Please select a valid option."))
                .count();

        assertTrue(invalidOptionOccurrences >= 3,
                "invalid option message should appear for the required, optional and multiple-selection steps");
        assertTrue(output.contains("Added: Brioche Bread - $2000"));
        assertTrue(output.contains("Added: Chicken - $7000"));
        assertTrue(output.contains("Added: Cheese - $2500"));
        assertTrue(output.contains("Added: Lettuce - $1000"));
        assertTrue(output.contains("Added: Tomato - $1000"));
        assertTrue(output.contains("Added: Bacon - $3000"));
        assertTrue(output.contains("Final price: $16500"));
    }

    @Test
    @Order(2)
    @DisplayName("should skip the optional cheese step and finish a multiple-selection step immediately with 0")
    void shouldSkipOptionalStepAndFinishMultipleSelectionImmediately() {
        String output = runAndCaptureOutput(TheFiveStarChefC2::run);

        assertTrue(output.contains("Added: Sesame Bread - $2500"));
        assertTrue(output.contains("Invalid option. Please select a valid option."));
        assertTrue(output.contains("Added: Beef - $8000"));
        assertTrue(output.contains("Skipped."));
        assertTrue(output.contains("Added: BBQ Sauce - $1500"));
        assertFalse(output.contains("Added: Lettuce"));
        assertFalse(output.contains("Added: Bacon"));
        assertTrue(output.contains("Final price: $12000"));
    }

    @Test
    @Order(3)
    @DisplayName("should run the whole program end to end through the main entry point without throwing")
    void shouldRunEndToEndThroughMainWithoutThrowing() {
        assertDoesNotThrow(() -> runAndCaptureOutput(() -> TheFiveStarChefC2.main(new String[0])));
    }
}
