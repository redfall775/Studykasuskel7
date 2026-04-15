package stok.menu;

public class Minuman extends MenuRestoran {
    private boolean dingin; // apakah disajikan dingin / es

    public Minuman(String id, String nama, int stok, double harga, boolean dingin) {
        super(id, nama, stok, harga);
        this.dingin = dingin;
    }

    public boolean isDingin() { return dingin; }

    @Override
    public String getKategori() { return "Minuman"; }

    @Override
    public String getKeterangan() {
        return "Penyajian: " + (dingin ? "Dingin / Es" : "Panas");
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("   └─ Keterangan: %s%n", getKeterangan());
    }
}
