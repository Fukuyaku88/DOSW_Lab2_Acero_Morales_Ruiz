package edu.eci.dosw.reto7;

public class WindowBlinds implements Device {

    private String name;
    private boolean isOpen;

    public WindowBlinds(String name, boolean isOpen){
        this.name = name;
        this.isOpen = isOpen;
    }

    public String open(){
        this.isOpen = true;
        return "Open blinds";
    }

    public String close(){
        this.isOpen = false;
        return "Close blinds";
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public String getState(){
        return isOpen ? "Open" : "Close";
    }
}
