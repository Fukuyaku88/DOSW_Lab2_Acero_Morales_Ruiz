package edu.eci.dosw.reto6;

public interface TechnicianHandler {
    String getName();
    void handle(Ticket ticket);
    void setNextHandler(TechnicianHandler handler);

}
