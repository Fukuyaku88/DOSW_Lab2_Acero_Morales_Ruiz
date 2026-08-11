package edu.eci.dosw.reto2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Ingredient")
class IngredientTest {

    @Test
    @DisplayName("should store and return the name provided in the constructor")
    void shouldReturnNameProvidedInConstructor() {
        Ingredient ingredient = new Ingredient("Cheese", 2500, IngredientType.CHEESE);

        assertEquals("Cheese", ingredient.getName());
    }

    @Test
    @DisplayName("should store and return the price provided in the constructor")
    void shouldReturnPriceProvidedInConstructor() {
        Ingredient ingredient = new Ingredient("Cheese", 2500, IngredientType.CHEESE);

        assertEquals(2500, ingredient.getPrice());
    }

    @Test
    @DisplayName("should store and return the type provided in the constructor")
    void shouldReturnTypeProvidedInConstructor() {
        Ingredient ingredient = new Ingredient("Cheese", 2500, IngredientType.CHEESE);

        assertEquals(IngredientType.CHEESE, ingredient.getType());
    }

    @Test
    @DisplayName("should build a description containing the name and the price formatted without decimals")
    void shouldBuildDescriptionWithNameAndFormattedPrice() {
        Ingredient ingredient = new Ingredient("Beef", 8000, IngredientType.MEAT);

        assertEquals("Beef - $8000", ingredient.getDescription());
    }

    @ParameterizedTest(name = "should format a price of {0} as \"{1}\" inside the description")
    @DisplayName("should format decimal prices by truncating to zero decimals")
    @CsvSource({
            "1000.0, $1000",
            "999.6, $1000",
            "0.0, $0",
            "1500.4, $1500"
    })
    void shouldFormatDecimalPricesInDescription(double price, String expectedFragment) {
        Ingredient ingredient = new Ingredient("Sauce", price, IngredientType.SAUCE);

        assertTrue(ingredient.getDescription().contains(expectedFragment));
    }

    @Test
    @DisplayName("should allow a zero price ingredient without throwing an exception")
    void shouldAllowZeroPriceIngredient() {
        Ingredient ingredient = new Ingredient("Free Sample", 0, IngredientType.OTHER);

        assertEquals(0, ingredient.getPrice());
    }

    @Test
    @DisplayName("should keep the description consistent with name and price even for repeated calls")
    void shouldReturnConsistentDescriptionOnRepeatedCalls() {
        Ingredient ingredient = new Ingredient("Lettuce", 1000, IngredientType.VEGETABLE);

        String firstCall = ingredient.getDescription();
        String secondCall = ingredient.getDescription();

        assertEquals(firstCall, secondCall);
    }
}
