package session7.tugas.product;

import java.util.ArrayList;
import java.util.List;

public class TestProducts {
    public static void main(String[] args) {
        // Create an ArrayList of Products
        List<Product> products = new ArrayList<>();

        // Add instances of Book and Electronics to the list
        products.add(new Book("Java Programming", 29.99, "James Gosling"));
        products.add(new Electronics("Smartphone", 599.99, 24));

        // Demonstrating Polymorphism
        for (Product product : products) {
            System.out.println(product.getProductDetails());
        }
    }
}
