package com.example;

public class SelfDrivingCar extends Car {
    public SelfDrivingCar(String chosenColor, String chosenInterior) {
        super(chosenColor, chosenInterior);
        System.out.println("Constructing self-driving car. Installing the car in Megafactory..");
    }

    @Override
    public void drive() {
        System.out.println("Driving to Bali...");
    }
}
