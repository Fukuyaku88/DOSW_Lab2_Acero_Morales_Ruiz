package edu.eci.dosw.reto6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TalkToTechnicalSupportC6Test {

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

        TalkToTechnicalSupportC6.run();

        return outputCapture.toString(StandardCharsets.UTF_8);
    }

    @Test
    void shouldPrintWelcomeMessageAndExitImmediately() {
        String output = runWithInput("EXIT\n");

        assertTrue(output.contains("bienvenido al soporte tecnico"));
    }

    @Test
    void shouldAddTicketSuccessfully() {
        String output = runWithInput("BASIC-LOW-Mouse danado\nEXIT\n");

        assertTrue(output.contains("El ticket numero 1, con nivel de dificultad BASIC, y nivel de prioridad; LOW fue agregado correctamente"));
    }

    @Test
    void shouldRejectInputWithWrongNumberOfTokens() {
        String output = runWithInput("BASIC-LOW\nEXIT\n");

        assertTrue(output.contains("Ingrese los 3 datos solicitados correctamente"));
    }

    @Test
    void shouldRejectInvalidEnumValues() {
        String output = runWithInput("SUPER-LOW-Problema raro\nEXIT\n");

        assertTrue(output.contains("no coincide con los valores disponibles"));
    }

    @Test
    void statisticsWithNoTicketsShouldWarnThereIsNothingToReview() {
        String output = runWithInput("STATISTICS\nEXIT\n");

        assertTrue(output.contains("No hay tickets que revisar"));
    }

    @Test
    void shouldClearTicketsAndResetIdCounter() {
        String output = runWithInput("BASIC-LOW-Mouse danado\nCLEAR\nBASIC-LOW-Teclado danado\nEXIT\n");

        assertTrue(output.contains("Sus tickets fueron eliminados con exito"));
        // Si el contador se reinicio, el segundo ticket vuelve a ser el numero 1
        assertTrue(output.contains("El ticket numero 1, con nivel de dificultad BASIC, y nivel de prioridad; LOW fue agregado correctamente"));
    }

    @Test
    void ticketSolvedDirectlyByFirstTechnicianShouldShowUpAsSolvedWithOneHandle() {
        // Saray (cabeza de la cadena) es BASIC/LOW, coincide exactamente -> lo resuelve ella sola
        String input = "BASIC-LOW-Mouse danado\nSTATISTICS\nEXIT\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Ticket number: 1 | SolvedBy: Saray Ovalle | Final State: SOLVED"));
        assertTrue(output.contains("Number of Resolved Tickets: 1"));
        assertTrue(output.contains("Number of Pending Tickets: 0"));
        assertTrue(output.contains("Most Common Priority of Resolved Tickets (MCPRT): LOW"));
        assertTrue(output.contains("Number of tickets with the MCPRT: 1"));
    }

    @Test
    void ticketNoOneCanSolveShouldEscalateThroughTheWholeChain() {
        // Ningun tecnico tiene ADVANCED/HIGH como especializacion -> queda pendiente tras pasar por los 3
        String input = "ADVANCED-HIGH-Problema critico\nSTATISTICS\nEXIT\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Ticket number: 1 | SolvedBy: No one; PENDING_ESCALATION | Final State: PENDING_ESCALATION"));
        assertTrue(output.contains("TICKETS REVIEWED BY MORE THAN ONE TECHNICIAN"));
        assertTrue(output.contains("TICKET'S THAT REMAINS UNSOLVED"));
        assertTrue(output.contains("Number of Resolved Tickets: 0"));
        assertTrue(output.contains("Number of Pending Tickets: 1"));
        assertTrue(output.contains("Most Common Priority of Resolved Tickets (MCPRT): No hay tickets resueltos."));
        assertTrue(output.contains("Number of tickets with the MCPRT: 0"));
    }
}
