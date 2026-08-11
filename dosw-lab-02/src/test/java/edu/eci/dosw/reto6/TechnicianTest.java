package edu.eci.dosw.reto6;

import edu.eci.dosw.reto6.enums.DifficultyLevel;
import edu.eci.dosw.reto6.enums.Priority;
import edu.eci.dosw.reto6.enums.TicketState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TechnicianTest {

    @Test
    void shouldReturnItsName() {
        Technician technician = new Technician("Ana", DifficultyLevel.BASIC, Priority.LOW);

        assertEquals("Ana", technician.getName());
    }

    @Test
    void shouldSolveTicketWhenCompetentInDifficultyAndPriority() {
        Technician technician = new Technician("Ana", DifficultyLevel.ADVANCED, Priority.HIGH);
        Ticket ticket = new Ticket(1, DifficultyLevel.BASIC, Priority.LOW, "Problema simple");

        technician.handle(ticket);

        assertEquals(TicketState.SOLVED, ticket.getState());
        assertEquals(1, ticket.getAmountOfHandles());
    }

    @Test
    void shouldSetTicketToPendingEscalationWhenNotCompetentAndNoNextHandler() {
        Technician technician = new Technician("Ana", DifficultyLevel.BASIC, Priority.LOW);
        Ticket ticket = new Ticket(1, DifficultyLevel.ADVANCED, Priority.HIGH, "Problema dificil");

        technician.handle(ticket);

        assertEquals(TicketState.PENDING_ESCALATION, ticket.getState());
        assertEquals(1, ticket.getAmountOfHandles());
    }

    @Test
    void shouldDelegateToNextHandlerWhenNotCompetent() {
        Technician second = new Technician("Bruno", DifficultyLevel.ADVANCED, Priority.HIGH);
        Technician first = new Technician("Ana", DifficultyLevel.BASIC, Priority.LOW);
        first.setNextHandler(second);

        Ticket ticket = new Ticket(1, DifficultyLevel.ADVANCED, Priority.HIGH, "Problema dificil");

        first.handle(ticket);

        assertEquals(TicketState.SOLVED, ticket.getState());
        // Bruno lo resuelve (+1) y Ana suma otro +1 al volver de la delegacion
        assertEquals(2, ticket.getAmountOfHandles());
    }

    @Test
    void shouldEscalateThroughFullChainWhenNoOneIsCompetent() {
        Technician third = new Technician("Karla", DifficultyLevel.ADVANCED, Priority.MEDIUM);
        Technician second = new Technician("Carlos", DifficultyLevel.INTERMEDIATE, Priority.MEDIUM);
        second.setNextHandler(third);
        Technician first = new Technician("Saray", DifficultyLevel.BASIC, Priority.LOW);
        first.setNextHandler(second);

        Ticket ticket = new Ticket(1, DifficultyLevel.ADVANCED, Priority.HIGH, "Problema muy dificil");

        first.handle(ticket);

        assertEquals(TicketState.PENDING_ESCALATION, ticket.getState());
        assertEquals(3, ticket.getAmountOfHandles());
    }

    @Test
    void shouldSolveInMiddleOfChainWithoutReachingTheEnd() {
        Technician third = new Technician("Karla", DifficultyLevel.ADVANCED, Priority.MEDIUM);
        Technician second = new Technician("Carlos", DifficultyLevel.INTERMEDIATE, Priority.MEDIUM);
        second.setNextHandler(third);
        Technician first = new Technician("Saray", DifficultyLevel.BASIC, Priority.LOW);
        first.setNextHandler(second);

        Ticket ticket = new Ticket(1, DifficultyLevel.INTERMEDIATE, Priority.LOW, "Problema medio");

        first.handle(ticket);

        assertEquals(TicketState.SOLVED, ticket.getState());
        assertEquals(2, ticket.getAmountOfHandles());
    }
}
