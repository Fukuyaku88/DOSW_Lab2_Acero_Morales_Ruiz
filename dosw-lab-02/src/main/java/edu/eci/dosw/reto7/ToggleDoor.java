package edu.eci.dosw.reto7;

public class ToggleDoor implements RCCommand{

    private Door door;
    private boolean prevIsOpen;

    public ToggleDoor(Door door){
        this.door = door;
    }

    @Override
    public String execute(){
        this.prevIsOpen = door.isOpen();
        return door.isOpen() ? door.close() : door.open();
    }

    @Override
    public String undo(){
        return this.prevIsOpen ? door.open() : door.close();
    }

    @Override
    public Device getDevice(){
        return this.door;
    }
}
