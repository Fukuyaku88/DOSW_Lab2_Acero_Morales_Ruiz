package edu.eci.dosw.reto6;

import edu.eci.dosw.reto6.enums.DifficultyLevel;
import edu.eci.dosw.reto6.enums.Priority;
import edu.eci.dosw.reto6.enums.TicketState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketTest {

    private Ticket ticket;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        ticket = new Ticket(1, DifficultyLevel.BASIC, Priority.LOW, "Mouse no enciende");
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldReturnDifficultyLevelSetInConstructor() {
        assertEquals(DifficultyLevel.BASIC, ticket.getDifficultyLevel());
    }

    @Test
    void shouldReturnPrioritySetInConstructor() {
        assertEquals(Priority.LOW, ticket.getPriority());
    }

    @Test
    void shouldStartWithZeroHandles() {
        assertEquals(0, ticket.getAmountOfHandles());
    }

    @Test
    void shouldIncreaseHandlesEachTimeAddOneHandleIsCalled() {
        ticket.addOneHandle();
        ticket.addOneHandle();

        assertEquals(2, ticket.getAmountOfHandles());
    }

    @Test
    void shouldStartWithNullState() {
        assertNull(ticket.getState());
    }

    @Test
    void shouldUpdateStateWhenSet() {
        ticket.setTicketstate(TicketState.SOLVED);

        assertEquals(TicketState.SOLVED, ticket.getState());
    }

    @Test
    void printStatisticsShouldThrowWhenStateWasNeverSet() {
        // El estado arranca en null y printStatistics llama state.name() sin validar.
        // Este test documenta el comportamiento actual de la clase (no necesariamente el deseado).
        assertThrows(NullPointerException.class, ticket::printStatistics);
    }

    @Test
    void printStatisticsShouldShowNoOneWhenTicketWasNeverSolved() {
        ticket.setTicketstate(TicketState.PENDING_ESCALATION);

        ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));

        ticket.printStatistics();

        String output = outputCapture.toString();
        assertTrue(output.contains("No one; PENDING_ESCALATION"));
        assertTrue(output.contains("Ticket number: 1"));
        assertTrue(output.contains("Final State: PENDING_ESCALATION"));
    }

    @Test
    void printStatisticsShouldShowTechnicianNameWhenSolved() {
        Technician technician = new Technician("Karla Sanchez", DifficultyLevel.ADVANCED, Priority.HIGH);
        ticket.setSolvedBy(technician);
        ticket.setTicketstate(TicketState.SOLVED);

        ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));

        ticket.printStatistics();

        String output = outputCapture.toString();
        assertTrue(output.contains("Karla Sanchez"));
        assertTrue(output.contains("Final State: SOLVED"));
    }
}
