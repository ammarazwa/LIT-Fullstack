package com.example;

public class Car {
    public String color = "Black";
    private int mNumberOfSeats = 5; // new private fields
    protected String mInterior = "leather";

    public Car() {
        // additional instructions go here
    }

    public Car(String chosenColor, String chosenInterior) {
        color = chosenColor;
        mInterior = chosenInterior;
    }

    public void drive() {
        System.out.println("Car is moving fast....");
    }

    public int getNumberOfSeats() {
        // You can also add more stuff here
        return mNumberOfSeats;
    }

    public String getInterior() {
        return mInterior;
    }

    private void turnOnTurbo() {

    }
}