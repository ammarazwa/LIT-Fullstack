package session5;

public class Car {
    private String color = "Black";
    private int numberOfSeats = 5;
    private String interior = "leather";
    private boolean turboEnabled = false;

    public Car() {
    }

    public Car(String chosenColor, String chosenInterior) {
        setColor(chosenColor);
        setInterior(chosenInterior);
    }

    public Car(String chosenColor, String chosenInterior, int seats, boolean turbo) {
        setColor(chosenColor);
        setInterior(chosenInterior);
        setNumberOfSeats(seats);
        this.turboEnabled = turbo;
    }

    public void drive() {
        String mode = turboEnabled ? "with TURBO" : "normal mode";
        System.out.println("Driving a " + color + " car (" + interior + " interior, " 
                + numberOfSeats + " seats) " + mode + ".");
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getInterior() {
        return interior;
    }

    public String getColor() {
        return color;
    }

    public boolean isTurboEnabled() {
        return turboEnabled;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("numberOfSeats must be > 0");
        }
        this.numberOfSeats = numberOfSeats;
    }

    public void setInterior(String interior) {
        if (interior == null || interior.isBlank()) {
            throw new IllegalArgumentException("interior must not be empty");
        }
        this.interior = interior;
    }

    public void setColor(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("color must not be empty");
        }
        this.color = color;
    }

    private void turnOnTurbo() {
        this.turboEnabled = true;
    }

    public void enableTurbo() {
        turnOnTurbo();
    }

    public void disableTurbo() {
        this.turboEnabled = false;
    }

    @Override
    public String toString() {
        return "Car{color='" + color + "', seats=" + numberOfSeats +
               ", interior='" + interior + "', turbo=" + turboEnabled + "}";
    }
}