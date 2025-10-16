package session7.tugas.shape;

public abstract class Shape {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    // Concrete method
    public String getColor() {
        return color;
    }

    // Abstract methods that subclasses must implement
    public abstract double getArea();
    public abstract double getPerimeter();
}
