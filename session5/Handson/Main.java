package session5.Handson;

public class Main {
    public static void main(String[] args) {
        PegawaiTetap Rina = new PegawaiTetap("Rina", 5000000, 1000000);
        PegawaiKontrak Budi = new PegawaiKontrak("Budi", 3000000, 10, 50000);

        Rina.tampilkanInfo();
        Budi.tampilkanInfo();

        System.out.println("\nJumlah total pegawai: " + Pegawai.getJumlahPegawai());
    }
}
