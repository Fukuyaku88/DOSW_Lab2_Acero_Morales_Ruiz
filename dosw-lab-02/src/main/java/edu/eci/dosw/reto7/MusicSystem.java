package edu.eci.dosw.reto7;

public class MusicSystem implements Device {

    private String name;
    private int currentV = 25;

    public MusicSystem(String name, int currentV){
        this.name = name;
        this.currentV = currentV;
    }

    public String adjVolume(int volume){
        this.currentV = volume;
        return "Volume: " + currentV;
    }

    public int getCurrentV(){
        return this.currentV;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public String getState(){
        return getCurrentV() + "% volume";
    }
}