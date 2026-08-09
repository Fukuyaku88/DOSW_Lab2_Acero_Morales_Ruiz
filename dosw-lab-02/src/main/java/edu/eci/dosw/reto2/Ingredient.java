package edu.eci.dosw.reto2;

public class Ingredient {

    private final String name;
    private final double price;
    private final IngredientType type;

    public Ingredient(String name, double price, IngredientType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public IngredientType getType() {
        return type;
    }

    public String getDescription() {
        return name + " - $" + String.format("%.0f", price);
    }
}