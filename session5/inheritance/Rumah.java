package session5.inheritance;

public class Rumah extends Bangunan {

    public Rumah(String nama) {
        super(nama);
    }

    @Override
    public void buatPondasi() {
        System.out.println(nama + ": Pondasi batu kali untuk rumah tinggal.");
    }

    @Override
    public void desainAtap() {
        System.out.println(nama + ": Atap pelana dengan genteng keramik.");
    }

    // Method tambahan spesifik rumah (opsional)
    public void pasangPagar() {
        System.out.println(nama + ": Memasang pagar rumah.");
    }
}
