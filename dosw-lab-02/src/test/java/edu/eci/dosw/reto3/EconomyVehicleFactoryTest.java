package edu.eci.dosw.reto3;

import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EconomyVehicleFactoryTest {

    private EconomyVehicleFactory factory;

    @BeforeEach
    void setUp() {
        factory = new EconomyVehicleFactory();
    }

    @Test
    void shouldCreateLandVehicleWithCorrectPrice() {
        Vehicle vehicle = factory.createLandVehicle(FamilyOfVehicle.LAND, CategoryOfVehicle.ECONOMY, ModelOfVehicle.CAR);

        assertEquals(80999.9, vehicle.getPrice());
    }

    @Test
    void shouldCreateLandVehicleWithAirConditioningOnly() {
        Vehicle vehicle = factory.createLandVehicle(FamilyOfVehicle.LAND, CategoryOfVehicle.ECONOMY, ModelOfVehicle.CAR);
        String description = vehicle.toString();

        assertTrue(description.contains("AIR_CONDITIONING"));
        assertFalse(description.contains("TELEVISION"));
        assertFalse(description.contains("TABLET"));
    }

    @Test
    void shouldCreateWaterVehicleWithCorrectPrice() {
        Vehicle vehicle = factory.createWaterVehicle(FamilyOfVehicle.WATER, CategoryOfVehicle.ECONOMY, ModelOfVehicle.MOTORBOAT);

        assertEquals(70500.0, vehicle.getPrice());
    }

    @Test
    void shouldCreateAirVehicleWithCorrectPrice() {
        Vehicle vehicle = factory.createAirVehicle(FamilyOfVehicle.AIR, CategoryOfVehicle.ECONOMY, ModelOfVehicle.AIRPLANE);

        assertEquals(450999.9, vehicle.getPrice());
    }

    @Test
    void createdVehicleShouldReflectRequestedFamilyCategoryAndModel() {
        Vehicle vehicle = factory.createWaterVehicle(FamilyOfVehicle.WATER, CategoryOfVehicle.ECONOMY, ModelOfVehicle.JETSKI);
        String description = vehicle.toString();

        assertTrue(description.contains("WATER"));
        assertTrue(description.contains("ECONOMY"));
        assertTrue(description.contains("JETSKI"));
    }
}
