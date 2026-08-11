package edu.eci.dosw.reto7;

public class Light implements Device{

    private String name;
    private boolean isOn = false;

    public Light(String name){
        this.name = name;
    }

    public String turnOn(){
        this.isOn = true;
        return "Light " + this.isOn;
    }

    public String turnOff(){
        this.isOn = false;
        return "Light " + this.isOn;
    }

    public boolean isOn(){
        return isOn;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public String getState(){
        return isOn ? "On" : "Off";
    }
}
