package edu.eci.dosw.reto3;

import java.util.HashSet;
import java.util.stream.Collectors;

import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;
import edu.eci.dosw.reto3.enums.TypeOfComfort;
import edu.eci.dosw.reto3.enums.TypeOfEquipment;

public class Vehicle {
    private FamilyOfVehicle family;
    private CategoryOfVehicle category;
    private ModelOfVehicle model;
    private double maximumSpeed;
    private TypeOfComfort comfort;
    private double price;
    private HashSet<TypeOfEquipment> equipments;

    Vehicle (FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model, double maximumSpeed, TypeOfComfort comfort, double price ) {
        this.family = family;
        this.category = category;
        this.model = model;
        this.maximumSpeed = maximumSpeed;
        this.comfort = comfort;
        this.price = price;
        equipments = new HashSet<TypeOfEquipment>();
    }

    public double getPrice() {
        return price;
    }

    private String getEquipmentInString() {
        return equipments.stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

    public void addEquipment(TypeOfEquipment equipment) {
        equipments.add(equipment);
    }

    @Override
    public String toString() {
        return " Family: " + family.name() + " | Category: " + category.name() + " | Model: " + model.name() + " | Max Speed: " + String.valueOf(maximumSpeed) + " km/h | Equipment: " + getEquipmentInString() + " | Price: " + String.valueOf(price);
    }
}
