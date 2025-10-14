package session4.enkapsulasi;

public class Dispenser {
    private int stokGalon;
    private int literGalon;
    private String merk;
    private String mode;

    public Dispenser(){
        
    }

    public Dispenser(int stokGalon, int literGalon, String merk, String mode) {
        this.stokGalon = stokGalon;
        this.literGalon = literGalon;
        this.merk = merk;
        this.mode = mode;
    }

    public int getStokGalon() {
        return stokGalon;
    }

    public void setStokGalon(int stokGalon) {
        this.stokGalon = stokGalon;
    }

    public int getLiterGalon() {
        return literGalon;
    }

    public void setLiterGalon(int literGalon) {
        this.literGalon = literGalon;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void displayDetails() {
        System.out.println("\nDetail Dispenser:");
        System.out.println("Merk: " + merk);
        System.out.println("Mode: " + mode);
        System.out.println("Stok Galon: " + stokGalon + " galon");
        System.out.println("Liter per Galon: " + literGalon + " liter");
    }

    public void useGalon() {
        if (stokGalon > 0) {
            stokGalon--;
            System.out.println("\nWARNING: Galon Habis");
            System.out.println("MEMASANG GALON BARU");
            System.out.println("Galon terpakai. Sisa stok: " + stokGalon);
        } else {
            System.out.println("Galon abis beli dulu ya di warung depan.");
        }
    }

    public void addStok(int additionalGalons) {
        stokGalon += additionalGalons;
        System.out.println("\nUPDATE: Udah beli galon");
        System.out.println("Menambah stok galon");
        System.out.println(additionalGalons + " galon baru ditambah ke stok. Updated stok galon: " + stokGalon);
    }
}
