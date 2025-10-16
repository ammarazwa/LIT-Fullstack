package session7.tugas.product;

public class Electronics extends Product {
    private int warrantyPeriod;  // in months

    public Electronics(String name, double price, int warrantyPeriod) {
        super(name, price);
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String getProductDetails() {
        return super.getProductDetails() + ", Warranty Period: " + warrantyPeriod + " months";
    }
}
