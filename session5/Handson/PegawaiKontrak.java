package session5.Handson;

public class PegawaiKontrak extends Pegawai {
    private int jamLembur;
    private double upahPerJam;

    public PegawaiKontrak(String nama, double gajiPokok, int jamLembur, double upahPerJam) {
        super(nama, gajiPokok);
        this.jamLembur = jamLembur;
        this.upahPerJam = upahPerJam;
    }

    @Override
    public double hitungGaji() {
        return gajiPokok + (jamLembur * upahPerJam);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("\nPegawai Kontrak");
        super.tampilkanInfo();
        System.out.println("Jam Lembur: " + jamLembur);
        System.out.println("Upah per Jam: Rp" + upahPerJam);
        System.out.println("Total Gaji: Rp" + hitungGaji());
        System.out.println("##################################");
    }
}
