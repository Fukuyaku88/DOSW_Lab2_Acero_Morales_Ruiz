package edu.eci.dosw.reto3;

import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;

public interface VehicleFactory {
    Vehicle createLandVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model);
    Vehicle createWaterVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model);
    Vehicle createAirVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model);
}
