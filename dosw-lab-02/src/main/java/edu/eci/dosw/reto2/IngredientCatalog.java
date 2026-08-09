package edu.eci.dosw.reto2;

import java.util.ArrayList;
import java.util.List;

public class IngredientCatalog {

    private static final List<Ingredient> INGREDIENTS = createIngredients();

    private IngredientCatalog() {
        // Prevent instantiation
    }

    private static List<Ingredient> createIngredients() {

        List<Ingredient> ingredients = new ArrayList<>();

        // Breads
        ingredients.add(
                new Ingredient("Brioche Bread", 2000, IngredientType.BREAD)
        );

        ingredients.add(
                new Ingredient("Sesame Bread", 2500, IngredientType.BREAD)
        );

        // Meats
        ingredients.add(
                new Ingredient("Beef", 8000, IngredientType.MEAT)
        );

        ingredients.add(
                new Ingredient("Chicken", 7000, IngredientType.MEAT)
        );

        // Cheese
        ingredients.add(
                new Ingredient("Cheese", 2500, IngredientType.CHEESE)
        );

        // Vegetables
        ingredients.add(
                new Ingredient("Lettuce", 1000, IngredientType.VEGETABLE)
        );

        ingredients.add(
                new Ingredient("Tomato", 1000, IngredientType.VEGETABLE)
        );

        ingredients.add(
                new Ingredient("Onion", 800, IngredientType.VEGETABLE)
        );

        // Sauces
        ingredients.add(
                new Ingredient("BBQ Sauce", 1500, IngredientType.SAUCE)
        );

        ingredients.add(
                new Ingredient("Ketchup", 1000, IngredientType.SAUCE)
        );

        // Other ingredients
        ingredients.add(
                new Ingredient("Bacon", 3000, IngredientType.OTHER)
        );

        return ingredients;
    }

    public static List<Ingredient> getAvailableIngredients() {
        return List.copyOf(INGREDIENTS);
    }

    public static List<Ingredient> getIngredientsByType(IngredientType type) {

        return INGREDIENTS.stream()
                .filter(ingredient -> ingredient.getType() == type)
                .toList();
    }
}