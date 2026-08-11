package edu.eci.dosw.reto2;

import java.util.ArrayList;
import java.util.List;

public class Hamburger {

    private final List<Ingredient> ingredients;

    public Hamburger(List<Ingredient> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }

    public List<Ingredient> getIngredients() {
        return List.copyOf(ingredients);
    }

    public double calculateTotalPrice() {
        return ingredients.stream()
                .mapToDouble(Ingredient::getPrice)
                .sum();
    }

    public String getSummary() {
        StringBuilder summary = new StringBuilder();

        summary.append("\n========== HAMBURGER SUMMARY ==========\n");
        summary.append("Selected ingredients:\n");

        for (Ingredient ingredient : ingredients) {
            summary.append("- ")
                    .append(ingredient.getDescription())
                    .append("\n");
        }

        summary.append("---------------------------------------\n");
        summary.append("Final price: $")
                .append(String.format("%.0f", calculateTotalPrice()))
                .append("\n");
        summary.append("=======================================\n");

        return summary.toString();
    }
}