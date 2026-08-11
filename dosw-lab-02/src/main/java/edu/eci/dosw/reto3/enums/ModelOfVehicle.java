package edu.eci.dosw.reto3.enums;

public enum ModelOfVehicle {
    CAR(FamilyOfVehicle.LAND),
	BICYCLE(FamilyOfVehicle.LAND),
    MOTORCYCLE(FamilyOfVehicle.LAND),
    MOTORBOAT(FamilyOfVehicle.WATER),
    SAILBOAT(FamilyOfVehicle.WATER),
    JETSKI(FamilyOfVehicle.WATER),
    AIRPLANE(FamilyOfVehicle.AIR),
    LIGHTAIRCRAFT(FamilyOfVehicle.AIR),
    HELICOPTER(FamilyOfVehicle.AIR);
	
	private final FamilyOfVehicle familyOfVehicle;
	
	ModelOfVehicle(FamilyOfVehicle familyOfVehicle) {
		this.familyOfVehicle = familyOfVehicle;
	}
	
	public FamilyOfVehicle getFamilyOfVehicle() {
		return familyOfVehicle;
	}
}