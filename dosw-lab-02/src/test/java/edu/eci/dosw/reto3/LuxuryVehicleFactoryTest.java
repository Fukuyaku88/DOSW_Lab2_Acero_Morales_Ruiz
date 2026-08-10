package edu.eci.dosw.reto3;

import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LuxuryVehicleFactoryTest {

    private LuxuryVehicleFactory factory;

    @BeforeEach
    void setUp() {
        factory = new LuxuryVehicleFactory();
    }

    @Test
    void shouldCreateLandVehicleWithCorrectPrice() {
        Vehicle vehicle = factory.createLandVehicle(FamilyOfVehicle.LAND, CategoryOfVehicle.LUXURY, ModelOfVehicle.CAR);

        assertEquals(150999.9, vehicle.getPrice());
    }

    @Test
    void shouldCreateLandVehicleWithAllLuxuryEquipment() {
        Vehicle vehicle = factory.createLandVehicle(FamilyOfVehicle.LAND, CategoryOfVehicle.LUXURY, ModelOfVehicle.CAR);
        String description = vehicle.toString();

        assertTrue(description.contains("TELEVISION"));
        assertTrue(description.contains("AIR_CONDITIONING"));
        assertTrue(description.contains("TABLET"));
    }

    @Test
    void shouldCreateWaterVehicleWithCorrectPrice() {
        Vehicle vehicle = factory.createWaterVehicle(FamilyOfVehicle.WATER, CategoryOfVehicle.LUXURY, ModelOfVehicle.SAILBOAT);

        assertEquals(129999.9, vehicle.getPrice());
    }

    @Test
    void shouldCreateAirVehicleWithCorrectPrice() {
        Vehicle vehicle = factory.createAirVehicle(FamilyOfVehicle.AIR, CategoryOfVehicle.LUXURY, ModelOfVehicle.HELICOPTER);

        assertEquals(750999.9, vehicle.getPrice());
    }

    @Test
    void createdVehicleShouldReflectRequestedFamilyCategoryAndModel() {
        Vehicle vehicle = factory.createAirVehicle(FamilyOfVehicle.AIR, CategoryOfVehicle.LUXURY, ModelOfVehicle.LIGHTAIRCRAFT);
        String description = vehicle.toString();

        assertTrue(description.contains("AIR"));
        assertTrue(description.contains("LUXURY"));
        assertTrue(description.contains("LIGHTAIRCRAFT"));
    }
}
