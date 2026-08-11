package edu.eci.dosw.reto5;

public abstract class BeverageDecorator implements Beverage{

    protected Beverage wrappee;

    public BeverageDecorator(Beverage wrappee){
        this.wrappee = wrappee;
    }

    public abstract String getDescription();
    public abstract double getPrice();
}
