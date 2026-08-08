package edu.eci.dosw.reto1;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> cartItems;
    private Customer customer;

    public ShoppingCart(Customer customer) {
        cartItems = new ArrayList<>();
        this.customer = customer;
    }

    public void addProduct(String product, int quantity) {
        Product prod = Inventory.getProduct(product);
        if (prod == null) {
            throw new IllegalArgumentException("There is not " + product);
        }

        CartItem item = new CartItem(prod, quantity);
        cartItems.add(item);
    }

    public double calculateSubTotal() {
        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public double calculateDiscount() {
        return calculateSubTotal() * customer.getDiscount() / 100;
    }

    public double calculateTotal() {
        return calculateSubTotal() - calculateDiscount();
    }

    public Receipt generateReceipt(){
        double subTotal = calculateSubTotal();
        double discount = calculateDiscount();
        double total = calculateTotal();

        Receipt newReceipt = new Receipt(new ArrayList<>(cartItems), subTotal, discount, total);

        // Clean shopping cart
        cartItems.clear();
        return newReceipt;
    }

}

record Product(String name, double unitPrice) {}

record CartItem(Product product, int quantity) {
    public double getTotalPrice(){
        return product.unitPrice() * quantity;
    }
    public Product getProduct(){
        return product;
    }
    public int getQ(){ return quantity; }
}