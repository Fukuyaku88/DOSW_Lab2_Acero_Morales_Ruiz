package edu.eci.dosw.reto2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("IngredientCatalog")
class IngredientCatalogTest {

    @Test
    @DisplayName("should return a non-empty list of available ingredients")
    void shouldReturnNonEmptyListOfIngredients() {
        List<Ingredient> ingredients = IngredientCatalog.getAvailableIngredients();

        assertFalse(ingredients.isEmpty());
    }

    @Test
    @DisplayName("should return exactly the eleven predefined ingredients")
    void shouldReturnExactlyElevenIngredients() {
        List<Ingredient> ingredients = IngredientCatalog.getAvailableIngredients();

        assertEquals(11, ingredients.size());
    }

    @Test
    @DisplayName("should return an immutable list that rejects modifications")
    void shouldReturnImmutableList() {
        List<Ingredient> ingredients = IngredientCatalog.getAvailableIngredients();

        assertThrows(UnsupportedOperationException.class,
                () -> ingredients.add(new Ingredient("Extra", 100, IngredientType.OTHER)));
    }

    @Test
    @DisplayName("should return the same ingredient descriptions on every call, reflecting a stable catalog")
    void shouldReturnConsistentDataAcrossCalls() {
        List<Ingredient> firstCall = IngredientCatalog.getAvailableIngredients();
        List<Ingredient> secondCall = IngredientCatalog.getAvailableIngredients();

        assertEquals(firstCall.size(), secondCall.size());
        for (int i = 0; i < firstCall.size(); i++) {
            assertEquals(firstCall.get(i).getDescription(), secondCall.get(i).getDescription());
            assertEquals(firstCall.get(i).getType(), secondCall.get(i).getType());
        }
    }

    @ParameterizedTest(name = "should return only ingredients of type {0} when filtering by that type")
    @DisplayName("should filter ingredients correctly for every ingredient type")
    @EnumSource(IngredientType.class)
    void shouldFilterIngredientsByEveryType(IngredientType type) {
        List<Ingredient> filtered = IngredientCatalog.getIngredientsByType(type);

        assertTrue(filtered.stream().allMatch(ingredient -> ingredient.getType() == type));
    }

    @Test
    @DisplayName("should return exactly two bread ingredients")
    void shouldReturnTwoBreadIngredients() {
        List<Ingredient> breads = IngredientCatalog.getIngredientsByType(IngredientType.BREAD);

        assertEquals(2, breads.size());
        Set<String> names = new HashSet<>();
        breads.forEach(ingredient -> names.add(ingredient.getName()));
        assertEquals(Set.of("Brioche Bread", "Sesame Bread"), names);
    }

    @Test
    @DisplayName("should return exactly one cheese ingredient")
    void shouldReturnOneCheeseIngredient() {
        List<Ingredient> cheeses = IngredientCatalog.getIngredientsByType(IngredientType.CHEESE);

        assertEquals(1, cheeses.size());
        assertEquals("Cheese", cheeses.get(0).getName());
    }

    @Test
    @DisplayName("should return exactly three vegetable ingredients")
    void shouldReturnThreeVegetableIngredients() {
        List<Ingredient> vegetables = IngredientCatalog.getIngredientsByType(IngredientType.VEGETABLE);

        assertEquals(3, vegetables.size());
        Set<String> names = new HashSet<>();
        vegetables.forEach(ingredient -> names.add(ingredient.getName()));
        assertEquals(Set.of("Lettuce", "Tomato", "Onion"), names);
    }

    @Test
    @DisplayName("should return an empty (but not null) list when no ingredient matches a type")
    void shouldNeverReturnNullForAnyType() {
        for (IngredientType type : IngredientType.values()) {
            assertNotNull(IngredientCatalog.getIngredientsByType(type));
        }
    }

    @Test
    @DisplayName("should not allow instantiation since the catalog is a static utility class")
    void shouldNotExposeAPublicConstructor() throws NoSuchMethodException {
        var constructor = IngredientCatalog.class.getDeclaredConstructor();

        assertFalse(constructor.canAccess(null));
    }
}
