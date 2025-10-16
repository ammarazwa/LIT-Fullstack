package session7.tugas.vehicle;

public class Vehicle {
    protected String brand;
    protected int year;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void start() {
        System.out.println("The vehicle is starting.");
    }

    public void stop() {
        System.out.println("The vehicle is stopping.");
    }

    // Display Info Method (Demonstrating Polymorphism)
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}
