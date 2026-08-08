package edu.eci.dosw.reto1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Customer {

    private String name;
    private CustomerType type;

    private ShoppingCart shopC;
    private final List<Receipt> receipts;

    public Customer(String name, CustomerType type){

        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        this.name = name;
        this.type = type;
        this.shopC = new ShoppingCart(this);
        this.receipts = new ArrayList<>();
    }

    public void addProduct(String product, int quantity){
        shopC.addProduct(product, quantity);
    }

    public double getDiscount(){ return type.getDiscount(); }

    public ShoppingCart getShoppingCart(){
        return shopC;
    }

    public void pay() {
        Receipt receipt  = shopC.generateReceipt();
        receipts.add(receipt);
    }

    public void printLastReceipt(){
        if(receipts.isEmpty()){
            throw new NoSuchElementException("There's nothing receipt available");
        }
        Receipt lastReceipt = receipts.get(receipts.size() - 1);
        System.out.println(lastReceipt.printString());
    }
}
