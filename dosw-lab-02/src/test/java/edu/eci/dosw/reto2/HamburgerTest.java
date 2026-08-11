package edu.eci.dosw.reto2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Hamburger")
class HamburgerTest {

    private Ingredient bread;
    private Ingredient meat;
    private Ingredient cheese;

    @BeforeEach
    void setUp() {
        bread = new Ingredient("Brioche Bread", 2000, IngredientType.BREAD);
        meat = new Ingredient("Beef", 8000, IngredientType.MEAT);
        cheese = new Ingredient("Cheese", 2500, IngredientType.CHEESE);
    }

    @Test
    @DisplayName("should return an empty ingredient list when built from an empty list")
    void shouldReturnEmptyListWhenBuiltFromEmptyList() {
        Hamburger hamburger = new Hamburger(new ArrayList<>());

        assertTrue(hamburger.getIngredients().isEmpty());
    }

    @Test
    @DisplayName("should return all ingredients that were passed to its constructor")
    void shouldReturnAllIngredientsPassedToConstructor() {
        Hamburger hamburger = new Hamburger(List.of(bread, meat, cheese));

        assertEquals(List.of(bread, meat, cheese), hamburger.getIngredients());
    }

    @Test
    @DisplayName("should return an immutable ingredient list that rejects modification")
    void shouldReturnImmutableIngredientList() {
        Hamburger hamburger = new Hamburger(List.of(bread, meat));

        assertThrows(UnsupportedOperationException.class, () -> hamburger.getIngredients().add(cheese));
    }

    @Test
    @DisplayName("should defensively copy the constructor list so later external mutations do not affect it")
    void shouldNotBeAffectedByMutatingOriginalListAfterConstruction() {
        List<Ingredient> mutableIngredients = new ArrayList<>(List.of(bread, meat));
        Hamburger hamburger = new Hamburger(mutableIngredients);

        mutableIngredients.add(cheese);

        assertEquals(List.of(bread, meat), hamburger.getIngredients());
    }

    @Test
    @DisplayName("should calculate a total price of zero when it has no ingredients")
    void shouldCalculateZeroPriceWithNoIngredients() {
        Hamburger hamburger = new Hamburger(new ArrayList<>());

        assertEquals(0.0, hamburger.calculateTotalPrice());
    }

    @Test
    @DisplayName("should calculate the total price as the sum of every ingredient's price")
    void shouldCalculateTotalPriceAsSumOfIngredientPrices() {
        Hamburger hamburger = new Hamburger(List.of(bread, meat, cheese));

        assertEquals(12500.0, hamburger.calculateTotalPrice());
    }

    @Test
    @DisplayName("should calculate the correct total price when only a single ingredient is present")
    void shouldCalculateTotalPriceWithSingleIngredient() {
        Hamburger hamburger = new Hamburger(List.of(meat));

        assertEquals(8000.0, hamburger.calculateTotalPrice());
    }

    @Test
    @DisplayName("should include a header and footer banner in the summary")
    void shouldIncludeHeaderAndFooterInSummary() {
        Hamburger hamburger = new Hamburger(List.of(bread));

        String summary = hamburger.getSummary();

        assertTrue(summary.contains("HAMBURGER SUMMARY"));
        assertTrue(summary.contains("Selected ingredients:"));
    }

    @Test
    @DisplayName("should list the description of every ingredient in the summary")
    void shouldListEveryIngredientDescriptionInSummary() {
        Hamburger hamburger = new Hamburger(List.of(bread, meat, cheese));

        String summary = hamburger.getSummary();

        assertTrue(summary.contains(bread.getDescription()));
        assertTrue(summary.contains(meat.getDescription()));
        assertTrue(summary.contains(cheese.getDescription()));
    }

    @Test
    @DisplayName("should include the total price formatted without decimals in the summary")
    void shouldIncludeFormattedFinalPriceInSummary() {
        Hamburger hamburger = new Hamburger(List.of(bread, meat, cheese));

        String summary = hamburger.getSummary();

        assertTrue(summary.contains("Final price: $12500"));
    }

    @Test
    @DisplayName("should produce a summary with no ingredient lines when there are no ingredients")
    void shouldProduceSummaryWithoutIngredientLinesWhenEmpty() {
        Hamburger hamburger = new Hamburger(new ArrayList<>());

        String summary = hamburger.getSummary();

        assertTrue(summary.contains("Final price: $0"));
        assertFalse(summary.contains("- $"));
    }
}
