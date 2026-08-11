package edu.eci.dosw.reto3;

import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsedVehicleFactoryTest {

    private UsedVehicleFactory factory;

    @BeforeEach
    void setUp() {
        factory = new UsedVehicleFactory();
    }

    @Test
    void shouldCreateLandVehicleWithCorrectPrice() {
        Vehicle vehicle = factory.createLandVehicle(FamilyOfVehicle.LAND, CategoryOfVehicle.USED, ModelOfVehicle.MOTORCYCLE);

        assertEquals(100999.9, vehicle.getPrice());
    }

    @Test
    void shouldCreateLandVehicleWithAirConditioningAndTabletOnly() {
        Vehicle vehicle = factory.createLandVehicle(FamilyOfVehicle.LAND, CategoryOfVehicle.USED, ModelOfVehicle.MOTORCYCLE);
        String description = vehicle.toString();

        assertTrue(description.contains("AIR_CONDITIONING"));
        assertTrue(description.contains("TABLET"));
        assertFalse(description.contains("TELEVISION"));
    }

    @Test
    void shouldCreateWaterVehicleWithCorrectPrice() {
        Vehicle vehicle = factory.createWaterVehicle(FamilyOfVehicle.WATER, CategoryOfVehicle.USED, ModelOfVehicle.MOTORBOAT);

        assertEquals(99850.9, vehicle.getPrice());
    }

    @Test
    void shouldCreateAirVehicleWithCorrectPrice() {
        Vehicle vehicle = factory.createAirVehicle(FamilyOfVehicle.AIR, CategoryOfVehicle.USED, ModelOfVehicle.AIRPLANE);

        assertEquals(550999.9, vehicle.getPrice());
    }

    @Test
    void createdVehicleShouldReflectRequestedFamilyCategoryAndModel() {
        Vehicle vehicle = factory.createLandVehicle(FamilyOfVehicle.LAND, CategoryOfVehicle.USED, ModelOfVehicle.BICYCLE);
        String description = vehicle.toString();

        assertTrue(description.contains("LAND"));
        assertTrue(description.contains("USED"));
        assertTrue(description.contains("BICYCLE"));
    }
}
