package edu.eci.dosw.reto1;

import java.util.List;

public class Receipt {

    private List<CartItem> purchasedProducts;
    private double totalPrice;
    private double appliedDiscount;
    private double finalAmountToPay;

    public Receipt(List<CartItem> cartItems, double subTotal,
                   double discount, double total){
        this.purchasedProducts = cartItems;
        this.totalPrice = subTotal;
        this.appliedDiscount = discount;
        this.finalAmountToPay = total;
    }

    public String printString(){
        StringBuilder tableBuilder = new StringBuilder();
        String divider         = " |------------------------------------------------------|\n";
        String headerFormat    = " | %-52s |\n";
        String rowFormat       = " | %-13s | %-11s | %-8s | %-11s |\n";
        String rowFormatDetail = " | %-24s | %-25s |\n";

        tableBuilder.append(divider);
        tableBuilder.append(String.format(headerFormat, "               DON PEPES'S STORE :)"));
        tableBuilder.append(divider);
        tableBuilder.append(String.format(rowFormat, "Product", "Price", "Quantity", "Total"));
        tableBuilder.append(divider);

        for (CartItem pP : purchasedProducts){
            String name = pP.getProduct().name();
            String price = String.valueOf(pP.getProduct().unitPrice());
            String q = String.valueOf(pP.getQ());

            tableBuilder.append(String.format(rowFormat,
                    name,"COP " + price,
                    q,
                    "COP " + pP.getTotalPrice()));
        }

        tableBuilder.append(divider);
        tableBuilder.append(String.format(headerFormat, "SUMMARY"));
        tableBuilder.append(divider);

        tableBuilder.append(String.format(rowFormatDetail, "Concept", "Value"));
        tableBuilder.append(divider);
        tableBuilder.append(String.format(rowFormatDetail, "SubTotal", "COP " + totalPrice));
        tableBuilder.append(String.format(rowFormatDetail, "Discount", "COP " + appliedDiscount));
        tableBuilder.append(String.format(rowFormatDetail, "Total", "COP " + finalAmountToPay));
        tableBuilder.append(divider);
        tableBuilder.append("              Thank you for trusting us ;)\n");

        return tableBuilder.toString();
    }


}
