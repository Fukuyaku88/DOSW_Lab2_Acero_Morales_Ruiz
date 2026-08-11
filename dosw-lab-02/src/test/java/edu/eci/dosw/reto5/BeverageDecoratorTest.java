package edu.eci.dosw.reto5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class BeverageDecoratorTest {

    @Test
    void shouldStoreWrappedBeverage() {
        Beverage base = new Coffee("Espresso", 5000);

        BeverageDecorator decorator = new BeverageDecorator(base) {
            @Override
            public String getDescription() {
                return wrappee.getDescription();
            }

            @Override
            public double getPrice() {
                return wrappee.getPrice();
            }
        };

        assertSame(base, decorator.wrappee);
    }

    @Test
    void shouldDelegateBehaviorToWrappeeByDefault() {
        Beverage base = new Coffee("Espresso", 5000);

        BeverageDecorator decorator = new BeverageDecorator(base) {
            @Override
            public String getDescription() {
                return wrappee.getDescription();
            }

            @Override
            public double getPrice() {
                return wrappee.getPrice();
            }
        };

        assertEquals("Espresso", decorator.getDescription());
        assertEquals(5000, decorator.getPrice());
    }
}
