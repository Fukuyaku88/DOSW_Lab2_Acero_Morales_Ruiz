package edu.eci.dosw.reto2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("CustomizedHamburgerBuilder")
class CustomizedHamburgerBuilderTest {

    private HamburgerBuilder builder;
    private Ingredient bread;
    private Ingredient meat;

    @BeforeEach
    void setUp() {
        builder = new CustomizedHamburgerBuilder();
        bread = new Ingredient("Brioche Bread", 2000, IngredientType.BREAD);
        meat = new Ingredient("Beef", 8000, IngredientType.MEAT);
    }

    @Test
    @DisplayName("should build a hamburger with no ingredients when none were added")
    void shouldBuildEmptyHamburgerWhenNoIngredientsAdded() {
        Hamburger hamburger = builder.build();

        assertTrue(hamburger.getIngredients().isEmpty());
    }

    @Test
    @DisplayName("should build a hamburger containing every ingredient that was added")
    void shouldBuildHamburgerWithAllAddedIngredients() {
        builder.addIngredient(bread).addIngredient(meat);

        Hamburger hamburger = builder.build();

        assertEquals(List.of(bread, meat), hamburger.getIngredients());
    }

    @Test
    @DisplayName("should return itself (this) from addIngredient to support fluent chaining")
    void shouldReturnSameInstanceToSupportFluentChaining() {
        HamburgerBuilder returned = builder.addIngredient(bread);

        assertSame(builder, returned);
    }

    @Test
    @DisplayName("should preserve the exact insertion order of the added ingredients")
    void shouldPreserveInsertionOrderOfIngredients() {
        Ingredient cheese = new Ingredient("Cheese", 2500, IngredientType.CHEESE);

        builder.addIngredient(meat).addIngredient(bread).addIngredient(cheese);

        Hamburger hamburger = builder.build();

        assertEquals(List.of(meat, bread, cheese), hamburger.getIngredients());
    }

    @Test
    @DisplayName("should allow adding the same ingredient more than once")
    void shouldAllowAddingSameIngredientMultipleTimes() {
        builder.addIngredient(bread).addIngredient(bread);

        Hamburger hamburger = builder.build();

        assertEquals(List.of(bread, bread), hamburger.getIngredients());
    }

    @Test
    @DisplayName("should produce independent hamburgers when build is called twice on separate builders")
    void shouldProduceIndependentHamburgersFromDifferentBuilders() {
        builder.addIngredient(bread);
        HamburgerBuilder otherBuilder = new CustomizedHamburgerBuilder();
        otherBuilder.addIngredient(meat);

        Hamburger firstHamburger = builder.build();
        Hamburger secondHamburger = otherBuilder.build();

        assertEquals(List.of(bread), firstHamburger.getIngredients());
        assertEquals(List.of(meat), secondHamburger.getIngredients());
    }

    @Test
    @DisplayName("should not let later addIngredient calls affect an already built hamburger")
    void shouldNotAffectAlreadyBuiltHamburgerWithLaterAdditions() {
        builder.addIngredient(bread);
        Hamburger firstBuild = builder.build();

        builder.addIngredient(meat);
        Hamburger secondBuild = builder.build();

        assertEquals(List.of(bread), firstBuild.getIngredients());
        assertEquals(List.of(bread, meat), secondBuild.getIngredients());
    }

    @Test
    @DisplayName("should calculate the correct total price for the ingredients added through the builder")
    void shouldCalculateCorrectTotalPriceForBuiltHamburger() {
        builder.addIngredient(bread).addIngredient(meat);

        Hamburger hamburger = builder.build();

        assertEquals(10000.0, hamburger.calculateTotalPrice());
    }
}
