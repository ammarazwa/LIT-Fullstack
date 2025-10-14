public class Product {
    String namaProduk;
    int harga;


    public Product(String namaProduk, int harga) {
        this.namaProduk = namaProduk;
        this.harga = harga;    
    }

    public void getProductDetails() {
        System.out.println("Nama Produk: " + namaProduk + ", Harga: " + harga);
    }
}