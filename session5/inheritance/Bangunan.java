package session5.inheritance;

public class Bangunan {
    protected String nama;
    public static int totalBangunan = 0;

    public Bangunan(String nama) {
        this.nama = nama;
        totalBangunan++;
    }

    public void buatPondasi() {
        System.out.println(nama + ": Membuat pondasi standar.");
    }

    public void desainAtap() {
        System.out.println(nama + ": Mendesain atap standar.");
    }

    public String getNama() {
        return nama;
    }
}
