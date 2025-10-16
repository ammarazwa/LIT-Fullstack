package session7.tugas.product;

public class Book extends Product {
    private String author;

    public Book(String name, double price, String author) {
        super(name, price);
        this.author = author;
    }

    @Override
    public String getProductDetails() {
        return super.getProductDetails() + ", Author: " + author;
    }
}
