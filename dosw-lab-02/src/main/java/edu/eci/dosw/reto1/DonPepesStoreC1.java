package edu.eci.dosw.reto1;

public class DonPepesStoreC1 {

    public static void run(){

        Inventory products = Inventory.getInstanceInventory();
        // Inventory
        products.addProduct("T-shirt", 20000);
        products.addProduct("Pants", 50000);
        products.addProduct("Cookies", 500);
        products.addProduct("Natural Juice", 3000);

        // New customer
        Customer pabloa = new Customer("Pabloa", CustomerType.FRECUENT);//, CustomerType.NEW;

        // Customer added products in his cart
        pabloa.addProduct("T-shirt", 2);
        pabloa.addProduct("Cookies", 3);
        pabloa.addProduct("Natural Juice", 5);
        pabloa.addProduct("Pants", 1);

        // Customer payed
        pabloa.pay();  // Saves the receipt, clean the cart
                          // print receipt

        pabloa.printLastReceipt();
    }
}