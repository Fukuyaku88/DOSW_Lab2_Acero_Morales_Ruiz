package edu.eci.dosw.reto3;

import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;
import edu.eci.dosw.reto3.enums.TypeOfComfort;
import edu.eci.dosw.reto3.enums.TypeOfEquipment;

public class EconomyVehicleFactory implements VehicleFactory {

    EconomyVehicleFactory () {
    }

    @Override
    public Vehicle createLandVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        Vehicle vehicle = new Vehicle(family, category, model, 185.5, TypeOfComfort.MINIMUM, 80999.9);
        vehicle.addEquipment(TypeOfEquipment.AIR_CONDITIONING);
        return vehicle;
    }

    @Override
    public Vehicle createWaterVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        Vehicle vehicle = new Vehicle(family, category, model, 105.8, TypeOfComfort.MINIMUM, 70500.0);
        vehicle.addEquipment(TypeOfEquipment.AIR_CONDITIONING);
        return vehicle;
    }

    @Override
    public Vehicle createAirVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        Vehicle vehicle = new Vehicle(family, category, model, 800.0, TypeOfComfort.MINIMUM, 450999.9);
        vehicle.addEquipment(TypeOfEquipment.AIR_CONDITIONING);
        return vehicle;
    }
}
