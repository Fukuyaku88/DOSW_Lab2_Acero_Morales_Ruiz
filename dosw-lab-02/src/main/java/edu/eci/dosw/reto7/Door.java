package edu.eci.dosw.reto7;

public class Door implements Device {

    private String name;
    private boolean isOpen;

    public Door(String name, boolean isOpen){
        this.name = name;
        this.isOpen = isOpen;
    }

    public String open(){
        this.isOpen = true;
        return "Open door";
    }

    public String close(){
        this.isOpen = false;
        return "Close door";
    }

    public boolean isOpen(){
        return isOpen;
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
