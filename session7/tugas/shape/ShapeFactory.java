package session7.tugas.shape;

public class ShapeFactory {
    public static Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle("Red", 10.0);
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle("Blue", 5.0, 8.0);
        }
        return null;
    }
}
