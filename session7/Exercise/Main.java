import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        
        products.add(new Book("Babel", 189000, "R.F. Kuang", "123-456789"));
        products.add(new Electronics("iPhone 13", 7999999, "Apple", 12));
        products.add(new Book("Si Putih", 104000, "Tere Liye", "978-234156"));
        products.add(new Electronics("Laptop", 1299999, "Lenovo", 24));

        for (Product product : products) {
            product.getProductDetails();
        }
    }
}