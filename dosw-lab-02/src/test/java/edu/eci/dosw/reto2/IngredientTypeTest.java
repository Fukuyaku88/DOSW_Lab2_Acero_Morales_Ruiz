package edu.eci.dosw.reto2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("IngredientType")
class IngredientTypeTest {

    @Test
    @DisplayName("should contain exactly the six expected ingredient categories")
    void shouldContainExactlySixCategories() {
        assertEquals(6, IngredientType.values().length);
    }

    @Test
    @DisplayName("should contain BREAD, MEAT, CHEESE, VEGETABLE, SAUCE and OTHER in that declared order")
    void shouldContainAllExpectedCategoriesInOrder() {
        IngredientType[] expected = {
                IngredientType.BREAD,
                IngredientType.MEAT,
                IngredientType.CHEESE,
                IngredientType.VEGETABLE,
                IngredientType.SAUCE,
                IngredientType.OTHER
        };

        assertArrayEquals(expected, IngredientType.values());
    }

    @Test
    @DisplayName("should resolve BREAD via valueOf using its exact name")
    void shouldResolveEnumConstantByName() {
        assertEquals(IngredientType.BREAD, IngredientType.valueOf("BREAD"));
    }
}
