package edu.eci.dosw.reto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheFiveStarChefC2 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    public static void run() {

        List<Ingredient> availableIngredients = createIngredients();

        System.out.println("=======================================");
        System.out.println("       FIVE-STAR CHEF BURGER");
        System.out.println("=======================================");

        showIngredients(availableIngredients);

        List<Ingredient> selectedIngredients = getUserSelection(availableIngredients);

        if (selectedIngredients.isEmpty()) {
            System.out.println("\nNo ingredients were selected.");
            System.out.println("The hamburger could not be created.");
            return;
        }

        HamburgerBuilder builder = new CustomizedHamburgerBuilder();

        for (Ingredient ingredient : selectedIngredients) {
            builder.addIngredient(ingredient);
        }

        Hamburger hamburger = builder.build();

        displayHamburger(hamburger);
    }

    private static List<Ingredient> createIngredients() {

        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(
                new Ingredient("Brioche Bread", 2000, IngredientType.BREAD)
        );

        ingredients.add(
                new Ingredient("Beef", 8000, IngredientType.MEAT)
        );

        ingredients.add(
                new Ingredient("Chicken", 7000, IngredientType.MEAT)
        );

        ingredients.add(
                new Ingredient("Cheese", 2500, IngredientType.CHEESE)
        );

        ingredients.add(
                new Ingredient("Lettuce", 1000, IngredientType.VEGETABLE)
        );

        ingredients.add(
                new Ingredient("Tomato", 1000, IngredientType.VEGETABLE)
        );

        ingredients.add(
                new Ingredient("Onion", 800, IngredientType.VEGETABLE)
        );

        ingredients.add(
                new Ingredient("BBQ Sauce", 1500, IngredientType.SAUCE)
        );

        ingredients.add(
                new Ingredient("Ketchup", 1000, IngredientType.SAUCE)
        );

        ingredients.add(
                new Ingredient("Bacon", 3000, IngredientType.OTHER)
        );

        return ingredients;
    }

    private static void showIngredients(List<Ingredient> ingredients) {

        System.out.println("\nAvailable ingredients:");

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    ingredient.getDescription() +
                    " [" + ingredient.getType() + "]"
            );
        }

        System.out.println("0. Finish selection");
    }

    private static List<Ingredient> getUserSelection(
            List<Ingredient> availableIngredients) {

        List<Ingredient> selectedIngredients = new ArrayList<>();

        while (true) {

            System.out.print("\nSelect an ingredient (0 to finish): ");

            int option = scanner.nextInt();

            if (option == 0) {
                break;
            }

            if (option < 1 || option > availableIngredients.size()) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            Ingredient selectedIngredient =
                    availableIngredients.get(option - 1);

            selectedIngredients.add(selectedIngredient);

            System.out.println(
                    "Added: " + selectedIngredient.getDescription()
            );
        }

        return selectedIngredients;
    }

    private static void displayHamburger(Hamburger hamburger) {

        System.out.println(hamburger.getSummary());
    }
}