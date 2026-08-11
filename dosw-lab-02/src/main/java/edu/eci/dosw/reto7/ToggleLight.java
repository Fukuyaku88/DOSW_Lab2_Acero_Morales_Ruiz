package edu.eci.dosw.reto7;

public class ToggleLight implements RCCommand {

    private Light light;
    private boolean prevIsOn;

    public ToggleLight(Light light){
        this.light = light;
    }

    @Override
    public String execute(){
        this.prevIsOn = light.isOn();
        return light.isOn() ? light.turnOff() : light.turnOn();
    }

    @Override
    public String undo(){
        return this.prevIsOn ? light.turnOn() : light.turnOff();
    }

    @Override
    public Device getDevice(){
        return this.light;
    }
}
