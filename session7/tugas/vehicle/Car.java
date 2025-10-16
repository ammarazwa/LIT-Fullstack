package session7.tugas.vehicle;

public class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String brand, int year, int numberOfDoors) {
        super(brand, year);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Calls the superclass method
        System.out.println("Number of doors: " + numberOfDoors);
    }

    public void honk() {
        System.out.println("The " + brand + " car is honking.");
    }
}
