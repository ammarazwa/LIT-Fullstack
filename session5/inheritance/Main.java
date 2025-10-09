package session5.inheritance;

public class Main {
    public static void main(String[] args) {
        Rumah rumah = new Rumah("Rumah Pak Rifal");
        rumah.buatPondasi();
        rumah.desainAtap();

        System.out.println();

        Gedung gedung = new Gedung("Gedung Universitas");
        gedung.masuk();
        gedung.buatPondasi();
        gedung.bangunAtap();
        gedung.bangunJalan();
        gedung.bangunJembatan();

        int totalBangunan = Bangunan.totalBangunan;
        System.out.println("\nTotal bangunan: " + totalBangunan);
    }
}
