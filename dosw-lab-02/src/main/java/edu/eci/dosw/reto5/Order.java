package edu.eci.dosw.reto5;

import javax.swing.plaf.BorderUIResource;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Order {

    private List<Beverage> orders;

    public Order(){
        this.orders = new ArrayList<>();
    }

    public void addBeverage(Beverage beverage){
        orders.add(beverage);
    }

    public List<Beverage> getOrders(){
        return this.orders;
    }

    public double getOrderPrice(){
        return orders.stream()
                .mapToDouble(Beverage::getPrice)
                .sum();
    }
}
