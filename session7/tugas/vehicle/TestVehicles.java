package session7.tugas.vehicle;

public class TestVehicles {
    public static void main(String[] args) {
        Vehicle myCar = new Car("Ford", 2020, 4);
        Vehicle myBike = new Bicycle("Schwinn", 2021, 18);

        // Demonstrating Polymorphism
        myCar.displayInfo();  // Will call the Car's displayInfo method
        System.out.println("---");
        myBike.displayInfo(); // Will call the Bicycle's displayInfo method
    }
}
