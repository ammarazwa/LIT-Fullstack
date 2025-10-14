package session4;

public class Car {
    public String color = "Black";
    private int fuel = 100;

    public String getColor(){
        return color;
    }

    public int getFuel(){
        return fuel;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setFuel(int fuel){
        if (fuel<0){
            System.out.println("Bensin tidak boleh di bawah 0");
        }
        this.fuel = fuel;
    }
}
