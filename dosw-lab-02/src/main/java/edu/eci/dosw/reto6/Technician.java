package edu.eci.dosw.reto6;

import edu.eci.dosw.reto6.enums.DifficultyLevel;
import edu.eci.dosw.reto6.enums.Priority;
import edu.eci.dosw.reto6.enums.TicketState;

public class Technician implements TechnicianHandler{
    private String name; 
    private DifficultyLevel difficultySpecialization;
    private Priority prioritySpecialization;
    private TechnicianHandler nextHandler;

    Technician (String name, DifficultyLevel difficultySpecialization, Priority prioritySpecialization) {
        this.name = name;
        this.difficultySpecialization = difficultySpecialization;
        this.prioritySpecialization = prioritySpecialization;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setNextHandler(TechnicianHandler handler) {
        nextHandler = handler;
    }

    @Override
    public void handle(Ticket ticket) {
        boolean competentInDifficulty = difficultySpecialization.compareTo(ticket.getDifficultyLevel()) >= 0;
        boolean competentInPriority = prioritySpecialization.compareTo(ticket.getPriority()) >= 0;

        if (competentInDifficulty && competentInPriority) {
            ticket.setTicketstate(TicketState.SOLVED);
            ticket.setSolvedBy(this);
            ticket.addOneHandle();
        } else if  (nextHandler != null) {
            nextHandler.handle(ticket);
            ticket.addOneHandle();
        } else {
            ticket.setTicketstate(TicketState.PENDING_ESCALATION);
            ticket.addOneHandle();
        }
    }

}
