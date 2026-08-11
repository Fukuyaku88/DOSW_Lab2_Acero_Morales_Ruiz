package edu.eci.dosw.reto2;

import java.util.List;
import java.util.Scanner;

public class TheFiveStarChefC2 {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        run();
    }

    public static void run() {

        System.out.println("=======================================");
        System.out.println("       FIVE-STAR CHEF BURGER");
        System.out.println("=======================================");

        HamburgerBuilder builder =
                new CustomizedHamburgerBuilder();

        // Step 1: Bread
        selectRequiredIngredient(
                builder,
                IngredientType.BREAD,
                "STEP 1 - SELECT YOUR BREAD"
        );

        // Step 2: Meat
        selectRequiredIngredient(
                builder,
                IngredientType.MEAT,
                "STEP 2 - SELECT YOUR MEAT"
        );

        // Step 3: Cheese
        selectOptionalIngredient(
                builder,
                IngredientType.CHEESE,
                "STEP 3 - SELECT YOUR CHEESE"
        );

        // Step 4: Vegetables
        selectMultipleIngredients(
                builder,
                IngredientType.VEGETABLE,
                "STEP 4 - SELECT YOUR VEGETABLES"
        );

        // Step 5: Sauces
        selectMultipleIngredients(
                builder,
                IngredientType.SAUCE,
                "STEP 5 - SELECT YOUR SAUCES"
        );

        // Step 6: Other ingredients
        selectMultipleIngredients(
                builder,
                IngredientType.OTHER,
                "STEP 6 - SELECT OTHER INGREDIENTS"
        );

        // Build final hamburger
        Hamburger hamburger = builder.build();

        displayHamburger(hamburger);
    }

    private static void selectRequiredIngredient(
            HamburgerBuilder builder,
            IngredientType type,
            String title) {

        List<Ingredient> options =
                IngredientCatalog.getIngredientsByType(type);

        System.out.println("\n" + title);
        System.out.println("---------------------------------------");

        for (int i = 0; i < options.size(); i++) {

            Ingredient ingredient = options.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    ingredient.getDescription()
            );
        }

        while (true) {

            System.out.print("\nSelect an option: ");

            int option = scanner.nextInt();

            if (option >= 1 && option <= options.size()) {

                Ingredient selectedIngredient =
                        options.get(option - 1);

                builder.addIngredient(selectedIngredient);

                System.out.println(
                        "Added: " +
                        selectedIngredient.getDescription()
                );

                break;
            }

            System.out.println(
                    "Invalid option. Please select a valid option."
            );
        }
    }

    private static void selectOptionalIngredient(
            HamburgerBuilder builder,
            IngredientType type,
            String title) {

        List<Ingredient> options =
                IngredientCatalog.getIngredientsByType(type);

        System.out.println("\n" + title);
        System.out.println("---------------------------------------");

        for (int i = 0; i < options.size(); i++) {

            Ingredient ingredient = options.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    ingredient.getDescription()
            );
        }

        System.out.println("0. Skip");

        while (true) {

            System.out.print("\nSelect an option: ");

            int option = scanner.nextInt();

            if (option == 0) {

                System.out.println("Skipped.");

                break;
            }

            if (option >= 1 && option <= options.size()) {

                Ingredient selectedIngredient =
                        options.get(option - 1);

                builder.addIngredient(selectedIngredient);

                System.out.println(
                        "Added: " +
                        selectedIngredient.getDescription()
                );

                break;
            }

            System.out.println(
                    "Invalid option. Please select a valid option."
            );
        }
    }

    private static void selectMultipleIngredients(
            HamburgerBuilder builder,
            IngredientType type,
            String title) {

        List<Ingredient> options =
                IngredientCatalog.getIngredientsByType(type);

        System.out.println("\n" + title);
        System.out.println("---------------------------------------");

        for (int i = 0; i < options.size(); i++) {

            Ingredient ingredient = options.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    ingredient.getDescription()
            );
        }

        System.out.println("0. Finish selection");

        while (true) {

            System.out.print("\nSelect an option: ");

            int option = scanner.nextInt();

            if (option == 0) {

                break;
            }

            if (option >= 1 && option <= options.size()) {

                Ingredient selectedIngredient =
                        options.get(option - 1);

                builder.addIngredient(selectedIngredient);

                System.out.println(
                        "Added: " +
                        selectedIngredient.getDescription()
                );

                continue;
            }

            System.out.println(
                    "Invalid option. Please select a valid option."
            );
        }
    }

    private static void displayHamburger(Hamburger hamburger) {

        System.out.println(hamburger.getSummary());
    }
}