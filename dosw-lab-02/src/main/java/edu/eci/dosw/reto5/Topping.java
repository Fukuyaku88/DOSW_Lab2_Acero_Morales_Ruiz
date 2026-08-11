package edu.eci.dosw.reto5;

public class Topping extends BeverageDecorator {

    private String name;
    private double ownPrice;

    public Topping(Beverage beverage, String name, double ownPrice){
        super(beverage);
        this.name = name;
        this.ownPrice = ownPrice;
    }

    @Override
    public String getDescription(){
        return super.wrappee.getDescription() + " + " + this.name;
    }

    @Override
    public double getPrice(){
        return super.wrappee.getPrice() + this.ownPrice;
    }

}
