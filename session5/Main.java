package session5;

public class Main {
    public static void main(String[] args) {
        Car defaultCar = new Car();
        System.out.println("🚗 Default Car: " + defaultCar);
        defaultCar.drive();

        Car redCar = new Car("Red", "fabric");
        System.out.println("\nRed Car: " + redCar);
        redCar.drive();

        Car sportCar = new Car("Blue", "leather", 2, true);
        System.out.println("\nSport Car: " + sportCar);
        sportCar.drive();

        sportCar.disableTurbo();
        sportCar.enableTurbo();
        sportCar.drive();
    }
}
