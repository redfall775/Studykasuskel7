package stok.menu;

public class Dessert extends MenuRestoran {
    private String rasa; // contoh: "Coklat", "Vanilla", "Strawberry"

    public Dessert(String id, String nama, int stok, double harga, String rasa) {
        super(id, nama, stok, harga);
        this.rasa = rasa;
    }

    public String getRasa() { return rasa; }

    @Override
    public String getKategori() { return "Dessert"; }

    @Override
    public String getKeterangan() {
        return "Rasa: " + rasa;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("   └─ Keterangan: %s%n", getKeterangan());
    }
}
