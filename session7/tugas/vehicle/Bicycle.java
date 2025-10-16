package session7.tugas.vehicle;

public class Bicycle extends Vehicle {
    private int numberOfGears;

    public Bicycle(String brand, int year, int numberOfGears) {
        super(brand, year);
        this.numberOfGears = numberOfGears;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Calls the superclass method
        System.out.println("Number of gears: " + numberOfGears);
    }

    public void ringBell() {
        System.out.println("The " + brand + " bicycle is ringing its bell.");
    }
}
