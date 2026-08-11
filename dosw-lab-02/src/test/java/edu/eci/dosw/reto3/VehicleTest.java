package edu.eci.dosw.reto3;

import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;
import edu.eci.dosw.reto3.enums.TypeOfComfort;
import edu.eci.dosw.reto3.enums.TypeOfEquipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehicleTest {

    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle(
                FamilyOfVehicle.LAND,
                CategoryOfVehicle.ECONOMY,
                ModelOfVehicle.CAR,
                180.0,
                TypeOfComfort.MINIMUM,
                80000.0
        );
    }

    @Test
    void shouldReturnCorrectPrice() {
        assertEquals(80000.0, vehicle.getPrice());
    }

    @Test
    void toStringShouldContainFamilyCategoryAndModel() {
        String description = vehicle.toString();

        assertTrue(description.contains("LAND"));
        assertTrue(description.contains("ECONOMY"));
        assertTrue(description.contains("CAR"));
    }

    @Test
    void toStringShouldContainMaxSpeedAndPrice() {
        String description = vehicle.toString();

        assertTrue(description.contains("180.0"));
        assertTrue(description.contains("80000.0"));
    }

    @Test
    void newVehicleShouldHaveNoEquipmentByDefault() {
        String description = vehicle.toString();

        int equipmentIndex = description.indexOf("Equipment: ") + "Equipment: ".length();
        int priceIndex = description.indexOf(" | Price");
        String equipmentSection = description.substring(equipmentIndex, priceIndex);

        assertEquals("", equipmentSection);
    }

    @Test
    void shouldAddSingleEquipment() {
        vehicle.addEquipment(TypeOfEquipment.TABLET);

        assertTrue(vehicle.toString().contains("TABLET"));
    }

    @Test
    void shouldAddMultipleDifferentEquipments() {
        vehicle.addEquipment(TypeOfEquipment.TABLET);
        vehicle.addEquipment(TypeOfEquipment.TELEVISION);

        String description = vehicle.toString();

        assertTrue(description.contains("TABLET"));
        assertTrue(description.contains("TELEVISION"));
    }

    @Test
    void shouldNotDuplicateEquipmentWhenAddedTwice() {
        vehicle.addEquipment(TypeOfEquipment.TABLET);
        vehicle.addEquipment(TypeOfEquipment.TABLET);

        String description = vehicle.toString();
        long occurrences = description.split("TABLET", -1).length - 1L;

        assertEquals(1, occurrences);
    }

    @Test
    void differentVehiclesShouldHaveIndependentEquipmentSets() {
        Vehicle other = new Vehicle(
                FamilyOfVehicle.WATER,
                CategoryOfVehicle.LUXURY,
                ModelOfVehicle.SAILBOAT,
                150.0,
                TypeOfComfort.MAXIMUM,
                129999.9
        );

        vehicle.addEquipment(TypeOfEquipment.TABLET);

        assertFalse(other.toString().contains("TABLET"));
    }
}
