package edu.eci.dosw.reto7;

public interface RCCommand {

    String execute();
    String undo();
    Device getDevice();
    default String getCName(){
        return this.getClass().getSimpleName();
    }
}
