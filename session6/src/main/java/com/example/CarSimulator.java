package com.example;

public class CarSimulator {
    public static void main(String[] args) {
        System.out.println("Launching car simulator...");

        Car myFerrari = new Car("blue", "leather");

        System.out.println("The sport car color is " + myFerrari.color);

        myFerrari.color = "Dark blue";

        System.out.println("After a new paint. The color of the car is " + myFerrari.color);

        Car myTesla = new Car();
        System.out.println(myTesla.color + " Tesla with black " + myTesla.getInterior() + " interior");

        SelfDrivingCar autobot = new SelfDrivingCar("Yellow", "Plastic");
        autobot.drive();
        System.out.println("The car color is: " + autobot.color);
        System.out.println("The car interior is: " + autobot.mInterior);
    }
}