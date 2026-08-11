package edu.eci.dosw.reto1;

import java.util.HashMap;

public class Inventory {

    private static HashMap<String, Product> inventory;
    private static final Inventory instance = new Inventory();

    private Inventory(){
        inventory = new HashMap();
    }

    public static Inventory getInstanceInventory() {
        return instance;
    }

    public void addProduct(String name, double unitPrice){
        Product product = new Product(name, unitPrice);
        inventory.put(name, product);
    }

    public static Product getProduct(String product){
        return inventory.get(product);
    }

}
