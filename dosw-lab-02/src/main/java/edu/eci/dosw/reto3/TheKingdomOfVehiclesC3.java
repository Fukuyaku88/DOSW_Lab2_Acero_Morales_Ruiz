package edu.eci.dosw.reto3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Scanner;

import edu.eci.dosw.reto3.enums.CategoryOfVehicle;
import edu.eci.dosw.reto3.enums.FamilyOfVehicle;
import edu.eci.dosw.reto3.enums.ModelOfVehicle;

public class TheKingdomOfVehiclesC3 {

    private static ArrayList<Vehicle> shoppingCart;
    private static VehicleFactory factory;

    public static void main(String[] args) {

        shoppingCart = new ArrayList<Vehicle>();

        String families = Arrays.stream(FamilyOfVehicle.values())
                                                    .map(Enum::name)
                                                    .collect(Collectors.joining(", "));;
        String categories = Arrays.stream(CategoryOfVehicle.values())
                                                    .map(Enum::name)
                                                    .collect(Collectors.joining(", "));;;
        String models = Arrays.stream(ModelOfVehicle.values())
                                                    .map(Enum::name)
                                                    .collect(Collectors.joining(", "));;;

        Scanner sc = new Scanner(System.in);

        System.out.println("¡Hola bienvenido a The Kingdom Of Vehicles!\n");

        boolean state = true;
        while (state) {
            System.out.println("Para ingresar un vehiculo especifique su Familia, Categoria y Modelo separados por un espacio y en ese orden\n");
            System.out.println("Familias; " + families);
            System.out.println("Categoria; " + categories);
            System.out.println("Modelo; " + models + "\n");
            System.out.println("Si desea ver su factura, ingrese 'PAY'");
            System.out.println("Si desea vaciar su carrito de compras, ingrese 'CLEAR'");
            System.out.println("Para salir, ingrese 'EXIT'\n");
            System.out.print("Ingrese su respuesta: ");
            String selected = sc.nextLine();

            if (selected.toUpperCase().equals("EXIT")) {
                state = false;
                break;
            } else if (selected.toUpperCase().equals("PAY")) {
                printReceipt();
            } else if (selected.toUpperCase().equals("CLEAR")) {
                clearShoppingCart();
            } else {
                String[] selectedArray = selected.trim(). split(" ");
                if (selectedArray.length != 3) {
                    System.out.println("\nIngrese los 3 datos solicitados correctamente\n");
                    continue;
                }
                try {
                    FamilyOfVehicle family = FamilyOfVehicle.valueOf(selectedArray[0].trim().toUpperCase());
                    CategoryOfVehicle category = CategoryOfVehicle.valueOf(selectedArray[1].trim().toUpperCase());
                    ModelOfVehicle model = ModelOfVehicle.valueOf(selectedArray[2].trim().toUpperCase());

                    addVehicle(family, category, model);
                    System.out.println( "\nEl vehiculo " + category + " " + model + " fue agregado correctamente\n");
                } catch (IllegalArgumentException e) {
                    System.out.println();
                    System.out.println("\nError: Uno o mas de los valores ingresados no coincide con los valores disponibles\n");
                    System.out.println();
                }
            }
        }

        sc.close();
    }

    private static void addVehicle(FamilyOfVehicle family, CategoryOfVehicle category, ModelOfVehicle model) {
        switch (category) {
            case ECONOMY:
                factory = new EconomyVehicleFactory();
                break;
            case USED:
                factory = new UsedVehicleFactory();
                break;
            case LUXURY:
                factory = new LuxuryVehicleFactory();
                break;
        }

        Vehicle vehicle = switch (family) {
            case LAND -> factory.createLandVehicle(family, category, model);
            case WATER -> factory.createWaterVehicle(family, category, model);
            case AIR -> factory.createAirVehicle(family, category, model);
        };

        shoppingCart.add(vehicle);
    }

    private static void clearShoppingCart() {
        shoppingCart = new ArrayList<Vehicle>();
        System.out.println("\nEl carrito fue vaciado correctamente\n");
    }

    private static void printReceipt() {
        if (shoppingCart.isEmpty()) {
            System.out.println("\nEl carrito esta vacio, no hay recibo que imprimir\n");
            return;
        }

        double subtotal = shoppingCart.stream()
                .mapToDouble(Vehicle::getPrice)
                .sum();;

        double discount;
        String stringDiscount;
        if (shoppingCart.size() >= 4) {
            discount = 0.9;
            stringDiscount = String.format("%.0f", (1 - discount) * 100) + "%" + " cause the purchase of 4 or more units in our shop";
        } else {
            discount = 1.0;
            stringDiscount = String.valueOf((1 - discount) * 10) + "%";
        }

        double finalTotal = subtotal * discount;
        
        System.out.println("\n=============================================================================================================");
        System.out.println("                                THE KINGDOM OF VEHICLES - RECEIPT                                            ");
        System.out.println("=============================================================================================================");
        shoppingCart.forEach(System.out::println);
        System.out.println("=============================================================================================================");
        System.out.println("SUBTOTAL: " + String.format("%.2f", subtotal));
        System.out.println("DISCOUNT: " + stringDiscount);
        System.out.println("FINAL TOTAL: " + String.format("%.2f", finalTotal));
        System.out.println("=============================================================================================================\n");
    }

    public static void run() {
        main(new String[0]);
    }
}
