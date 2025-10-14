public class Book extends Product {
    String penulis;
    String isbn;

    public Book(String namaProduk, int harga, String penulis, String isbn) {
        super(namaProduk, harga);
        this.penulis = penulis;
        this.isbn = isbn;
    }

    @Override
    public void getProductDetails() {
        System.out.println("Nama Buku : " + namaProduk + ", Penulis: " + penulis + ", ISBN: " + isbn + ", Harga: " + harga);
    }
}
