package edu.eci.dosw.reto2;

public interface HamburgerBuilder {

    HamburgerBuilder addIngredient(Ingredient ingredient);

    Hamburger build();
}