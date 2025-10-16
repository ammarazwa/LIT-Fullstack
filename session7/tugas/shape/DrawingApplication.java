package session7.tugas.shape;

import java.util.ArrayList;
import java.util.List;

public class DrawingApplication {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        // Create shapes using the factory
        Shape shape1 = ShapeFactory.getShape("CIRCLE");
        Shape shape2 = ShapeFactory.getShape("RECTANGLE");

        shapes.add(shape1);
        shapes.add(shape2);

        // Process shapes polymorphically
        for (Shape shape : shapes) {
            System.out.println("Shape: " + shape.getClass().getSimpleName());
            System.out.println("Color: " + shape.getColor());
            System.out.println("Area: " + shape.getArea());
            System.out.println("Perimeter: " + shape.getPerimeter());
            System.out.println();
        }
    }
}
