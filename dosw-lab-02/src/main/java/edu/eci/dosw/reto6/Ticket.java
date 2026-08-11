package edu.eci.dosw.reto6;

import edu.eci.dosw.reto6.enums.DifficultyLevel;
import edu.eci.dosw.reto6.enums.Priority;
import edu.eci.dosw.reto6.enums.TicketState;

public class Ticket {
    private int id;
    private DifficultyLevel difficultyLevel;
    private Priority priority;
    private String description;
    private int amountOfHandles;
    private TechnicianHandler solvedBy;
    private TicketState state;

    Ticket(int id, DifficultyLevel difficultyLevel, Priority priority, String description) {
        this.id = id;
        this.difficultyLevel = difficultyLevel;
        this.priority = priority;
        this.description = description;
        amountOfHandles = 0;
        solvedBy = null;
        state = null;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public Priority getPriority() {
        return priority;
    }

    public int getAmountOfHandles() {
        return amountOfHandles;
    }

    public TicketState getState() {
        return state;
    }

    public void setTicketstate(TicketState state) {
        this.state = state;
    }

    public void setSolvedBy(TechnicianHandler technician) {
        solvedBy = technician;
    }

    public void addOneHandle() {
        amountOfHandles ++;
    }

    public void printStatistics() {
        String finalSolvedBy;
        if (solvedBy == null) {
            finalSolvedBy = "No one; " + TicketState.PENDING_ESCALATION.name();
        } else {
            finalSolvedBy = solvedBy.getName();
        }
        System.out.println("Ticket number: " + String.valueOf(id) + " | SolvedBy: " + finalSolvedBy + " | Final State: " + state.name());
    }
}
