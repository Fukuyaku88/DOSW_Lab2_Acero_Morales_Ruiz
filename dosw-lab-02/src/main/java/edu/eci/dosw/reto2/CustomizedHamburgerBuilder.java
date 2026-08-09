package edu.eci.dosw.reto2;

import java.util.ArrayList;
import java.util.List;

public class CustomizedHamburgerBuilder implements HamburgerBuilder {

    private final List<Ingredient> ingredients;

    public CustomizedHamburgerBuilder() {
        this.ingredients = new ArrayList<>();
    }

    @Override
    public HamburgerBuilder addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    @Override
    public Hamburger build() {
        return new Hamburger(ingredients);
    }
}