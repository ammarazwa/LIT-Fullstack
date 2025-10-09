package session5.inheritance;
public class Gedung extends Bangunan {

    public Gedung(String nama) {
        super(nama);
    }

    public void masuk() {
        System.out.println("Masuk ke " + nama + ".");
    }

    @Override
    public void buatPondasi() {
        System.out.println(nama + ": Pondasi tiang pancang untuk gedung bertingkat.");
    }

    public void bangunAtap() {
        System.out.println(nama + ": Membangun atap baja ringan + waterproofing.");
    }

    public void bangunJalan() {
        System.out.println(nama + ": Membangun akses jalan menuju gedung.");
    }

    public void bangunJembatan() {
        System.out.println(nama + ": Membangun jembatan penghubung antar-gedung.");
    }
}
