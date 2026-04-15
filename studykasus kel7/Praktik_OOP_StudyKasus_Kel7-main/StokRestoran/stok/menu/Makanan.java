package stok.menu;

public class Makanan extends MenuRestoran {
    private String jenisHidangan; // contoh: "Nasi", "Mie", "Lauk"

    public Makanan(String id, String nama, int stok, double harga, String jenisHidangan) {
        super(id, nama, stok, harga);
        this.jenisHidangan = jenisHidangan;
    }

    public String getJenisHidangan() { return jenisHidangan; }

    @Override
    public String getKategori() { return "Makanan"; }

    @Override
    public String getKeterangan() {
        return String.format("Jenis Hidangan: %s", jenisHidangan);
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("   └─ Keterangan: %s%n", getKeterangan());
    }
}
