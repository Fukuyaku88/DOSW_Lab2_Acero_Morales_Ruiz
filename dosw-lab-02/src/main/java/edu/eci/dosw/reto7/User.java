package edu.eci.dosw.reto7;

public class User {

    private String userID;
    private String name;

    public User(String id, String name){
        this.userID = id;
        this.name = name;
    }

    public String getID(){
        return this.userID;
    }

    public String getName(){
        return this.name;
    }


}
