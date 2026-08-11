package edu.eci.dosw.reto3;

import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;
import edu.eci.dosw.reto3.enums.TypeOfComfort;
import edu.eci.dosw.reto3.enums.TypeOfEquipment;

public class LuxuryVehicleFactory implements VehicleFactory {

    LuxuryVehicleFactory () {
    }

    @Override
    public Vehicle createLandVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        Vehicle vehicle = new Vehicle(family, category, model, 250.5, TypeOfComfort.MAXIMUM, 150999.9);
        vehicle.addEquipment(TypeOfEquipment.TELEVISION);
        vehicle.addEquipment(TypeOfEquipment.AIR_CONDITIONING);
        vehicle.addEquipment(TypeOfEquipment.TABLET);
        return vehicle;
    }

    @Override
    public Vehicle createWaterVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        Vehicle vehicle = new Vehicle(family, category, model, 150.8, TypeOfComfort.MAXIMUM, 129999.9);
        vehicle.addEquipment(TypeOfEquipment.TELEVISION);
        vehicle.addEquipment(TypeOfEquipment.AIR_CONDITIONING);
        vehicle.addEquipment(TypeOfEquipment.TABLET);
        return vehicle;
    }

    @Override
    public Vehicle createAirVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        Vehicle vehicle = new Vehicle(family, category, model, 850.2, TypeOfComfort.MAXIMUM, 750999.9);
        vehicle.addEquipment(TypeOfEquipment.TELEVISION);
        vehicle.addEquipment(TypeOfEquipment.AIR_CONDITIONING);
        vehicle.addEquipment(TypeOfEquipment.TABLET);
        return vehicle;
    }
}