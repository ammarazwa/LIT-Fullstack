public class Electronics extends Product {
    String brand;
    int garansi; 

    public Electronics(String namaProduk, int harga, String brand, int garansi) {
        super(namaProduk, harga);
        this.brand = brand;
        this.garansi = garansi;
    }

    @Override
    public void getProductDetails() {
        System.out.println("Nama Elektronik: " + namaProduk + ", Brand: " + brand + ", Garansi: " + garansi + " bulan, Harga: " + harga);
    }
}