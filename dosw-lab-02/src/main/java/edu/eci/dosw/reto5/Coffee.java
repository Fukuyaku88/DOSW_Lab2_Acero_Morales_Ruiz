package edu.eci.dosw.reto5;

public class Coffee implements Beverage {

    private final String name;
    private double basePrice;

    public Coffee(String name, double basePrice){
        this.name = name;
        this.basePrice = basePrice;
    }

    @Override
    public String getDescription(){
        return this.name;
    }

    @Override
    public double getPrice(){
        return this.basePrice;
    }
}
