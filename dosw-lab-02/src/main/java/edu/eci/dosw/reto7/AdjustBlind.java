package edu.eci.dosw.reto7;

public class AdjustBlind implements RCCommand{

    private WindowBlinds windowB;
    private boolean prevIsOpen;

    public AdjustBlind(WindowBlinds windowB){
        this.windowB = windowB;
    }

    @Override
    public String execute(){
        this.prevIsOpen = windowB.isOpen();
        return windowB.isOpen() ? windowB.close() : windowB.open();
    }

    @Override
    public String undo(){
        return this.prevIsOpen ? windowB.open() : windowB.close();
    }

    @Override
    public Device getDevice(){
        return this.windowB;
    }

}
