package edu.eci.dosw.reto1;

public enum CustomerType {

    NEW(5),
    FRECUENT(10);

    private final double discount;
    CustomerType(double discount){ this.discount = discount; }

    public double getDiscount() {
        return discount;
    }
}
