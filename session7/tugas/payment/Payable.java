package session7.tugas.payment;

public interface Payable {
    double getPaymentAmount(); // Abstract method

    // A default method (Java 8+) with a body
    default String getPaymentDetails() {
        return "Payment Amount: $" + getPaymentAmount();
    }
}
