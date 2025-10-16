package session7.tugas.payment;

public class Invoice implements Payable {
    private String partNumber;
    private int quantity;
    private double pricePerItem;

    public Invoice(String partNumber, int quantity, double pricePerItem) {
        this.partNumber = partNumber;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return quantity * pricePerItem;
    }
}
