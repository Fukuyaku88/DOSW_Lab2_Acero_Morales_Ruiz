package edu.eci.dosw.reto3;

import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;
import edu.eci.dosw.reto3.enums.TypeOfComfort;
import edu.eci.dosw.reto3.enums.TypeOfEquipment;

public class UsedVehicleFactory implements VehicleFactory {

    UsedVehicleFactory () {
    }

    @Override
    public Vehicle createLandVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        Vehicle vehicle = new Vehicle(family, category, model, 200.5, TypeOfComfort.MEDIUM, 100999.9);
        vehicle.addEquipment(TypeOfEquipment.AIR_CONDITIONING);
        vehicle.addEquipment(TypeOfEquipment.TABLET);
        return vehicle;
    }

    @Override
    public Vehicle createWaterVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        Vehicle vehicle = new Vehicle(family, category, model, 135.8, TypeOfComfort.MEDIUM, 99850.9);
        vehicle.addEquipment(TypeOfEquipment.AIR_CONDITIONING);
        vehicle.addEquipment(TypeOfEquipment.TABLET);
        return vehicle;
    }

    @Override
    public Vehicle createAirVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        Vehicle vehicle = new Vehicle(family, category, model, 820.2, TypeOfComfort.MEDIUM, 550999.9);
        vehicle.addEquipment(TypeOfEquipment.AIR_CONDITIONING);
        vehicle.addEquipment(TypeOfEquipment.TABLET);
        return vehicle;
    }
}