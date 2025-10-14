package session4.enkapsulasi;

public class Main {
     public static void main(String[] args) {
        Dispenser dispenser = new Dispenser(2, 19, "KIRIN", "Hot And Normal Water Dispenser");

        dispenser.displayDetails();

        dispenser.useGalon();
        dispenser.useGalon();
        dispenser.useGalon();
        
        dispenser.addStok(1);


        dispenser.displayDetails();
     }
}
