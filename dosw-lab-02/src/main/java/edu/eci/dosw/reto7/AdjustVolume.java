package edu.eci.dosw.reto7;

public class AdjustVolume implements RCCommand{

    private MusicSystem musicSystem;
    private int previousV;
    private int targetV;

    public AdjustVolume(MusicSystem musicSystem, int targetV){
        this.musicSystem = musicSystem;
        this.targetV = targetV;
    }

    @Override
    public String execute(){
        this.previousV = musicSystem.getCurrentV();
        return musicSystem.adjVolume(targetV);
    }

    @Override
    public String undo(){
        return musicSystem.adjVolume(previousV);
    }

    @Override
    public Device getDevice(){
        return this.musicSystem;
    }
}
