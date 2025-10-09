package session5.Handson;

public class Pegawai {
    protected String nama;
    protected double gajiPokok;
    private static int jumlahPegawai = 0;

    public Pegawai(String nama, double gajiPokok) {
        this.nama = nama;
        this.gajiPokok = gajiPokok;
        jumlahPegawai++;
    }

    public double hitungGaji() {
        return gajiPokok;
    }

    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Gaji Pokok: Rp" + gajiPokok);
    }
}
