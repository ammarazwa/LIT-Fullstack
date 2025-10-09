package session5.Handson;

public class PegawaiTetap extends Pegawai {
    private double tunjangan;

    public PegawaiTetap(String nama, double gajiPokok, double tunjangan) {
        super(nama, gajiPokok);
        this.tunjangan = tunjangan;
    }

    @Override
    public double hitungGaji() {
        return gajiPokok + tunjangan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("\nPegawai Tetap");
        super.tampilkanInfo();
        System.out.println("Tunjangan: Rp" + tunjangan);
        System.out.println("Total Gaji: Rp" + hitungGaji());
        System.out.println("##############################################");
    }
}
