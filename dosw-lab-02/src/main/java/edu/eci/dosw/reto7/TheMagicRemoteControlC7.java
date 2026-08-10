package edu.eci.dosw.reto7;

import java.awt.*;

public class TheMagicRemoteControlC7 {

    public static void run(){

        // Devices
        MusicSystem musicS1 = new MusicSystem("MS1", 50);
        Door door1 = new Door("D1", false);
        WindowBlinds windowB1 = new WindowBlinds("WB1", true);
        Light light1 = new Light("L1");
        // Users
        User mom = new User("0001", "mom");
        User dad = new User("0002", "dad");
        User son = new User("0003", "son");
        // New remote Control
        RemoteControl remoteC = new RemoteControl();
        // Music System Actions
        System.out.println("Actions\n");
        System.out.println(remoteC.executeAction(new AdjustVolume(musicS1, 25), mom));
        System.out.println(remoteC.executeAction(new AdjustVolume(musicS1, 45), dad));
        System.out.println(remoteC.executeAction(new AdjustVolume(musicS1, 99), son));
        // Door Actions
        System.out.println(remoteC.executeAction(new ToggleDoor(door1), mom));
        System.out.println(remoteC.executeAction(new ToggleDoor(door1), dad));
        System.out.println(remoteC.undoLastAction(son));

        // Window Blinds Actions
        System.out.println(remoteC.executeAction(new AdjustBlind(windowB1), son));
        System.out.println(remoteC.executeAction(new AdjustBlind(windowB1), dad));
        System.out.println(remoteC.undoLastAction(mom));
        // Light Actions
        System.out.println(remoteC.executeAction(new ToggleLight(light1), son));
        System.out.println(remoteC.executeAction(new ToggleLight(light1), dad));
        System.out.println(remoteC.executeAction(new ToggleLight(light1), mom) + "\n");
        // Remote history
        remoteC.printActionHistory();

    }
}
