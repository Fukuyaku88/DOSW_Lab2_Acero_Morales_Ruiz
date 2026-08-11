package edu.eci.dosw.reto5;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.text.CollationElementIterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomizedCoffeC5 {

    public static void run(){

        // New order
        Order order1 = new Order();

        Beverage coffee1 = new Coffee("Expresso", 5000);

        // We will add toppings
        coffee1 = new Topping(coffee1, "Milk", 1000);
        coffee1 = new Topping(coffee1, "Chocolate", 1500);
        coffee1 = new Topping(coffee1, "Caramel", 1200);

        Beverage coffee2 = new Coffee("Cappuccino", 8000);

        coffee2 = new Topping(coffee2, "Whipped Cream", 2000);
        coffee2 = new Topping(coffee2, "Mint", 1300);
        coffee2 = new Topping(coffee2, "Condense Milk", 3500);

        // Add both into order
        order1.addBeverage(coffee1);
        order1.addBeverage(coffee2);

        System.out.println("Total Order Prices\n");
        order1.getOrders().stream()
                .forEach(order -> System.out.println(
                        "Order: " + order.getDescription()
                                + "\n -> Price: COP "
                                + (int)order.getPrice()
                ));

        System.out.println("\nTotal Price: " + (int)order1.getOrderPrice());
    }
}
