package stok.menu;

public abstract class MenuRestoran {
    protected String id;
    protected String nama;
    protected int stok;
    protected double harga;

    public MenuRestoran(String id, String nama, int stok, double harga) {
        this.id = id;
        setNama(nama);
        setStok(stok);
        setHarga(harga);
    }

    // Getter
    public String getId()    { return id; }
    public String getNama()  { return nama; }
    public int getStok()     { return stok; }
    public double getHarga() { return harga; }

    // Setter dengan validasi
    public void setNama(String nama) {
        if (nama == null || nama.isBlank())
            throw new IllegalArgumentException("Nama menu tidak boleh kosong!");
        this.nama = nama;
    }

    public void setStok(int stok) {
        if (stok < 0)
            throw new IllegalArgumentException("Stok tidak boleh negatif!");
        this.stok = stok;
    }

    public void setHarga(double harga) {
        if (harga <= 0)
            throw new IllegalArgumentException("Harga harus lebih dari 0!");
        this.harga = harga;
    }

    // Tambah & kurangi stok
    public void tambahStok(int jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah tambah stok harus lebih dari 0!");
            return;
        }
        this.stok += jumlah;
        System.out.printf("Stok \"%s\" berhasil ditambah %d. Total stok: %d%n", nama, jumlah, this.stok);
    }

    public boolean kurangiStok(int jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah pengurangan harus lebih dari 0!");
            return false;
        }
        if (jumlah > this.stok) {
            System.out.printf("Stok \"%s\" tidak cukup! Stok tersedia: %d%n", nama, this.stok);
            return false;
        }
        this.stok -= jumlah;
        System.out.printf("Stok \"%s\" berkurang %d. Sisa stok: %d%n", nama, jumlah, this.stok);
        return true;
    }

    public boolean isStokHabis() {
        return this.stok == 0;
    }

    // Method abstract → subclass wajib implement
    public abstract String getKategori();
    public abstract String getKeterangan();

    public void tampilkanInfo() {
        System.out.printf("ID: %-10s | Nama: %-25s | Kategori: %-10s | Stok: %-5d | Harga: Rp %,.0f%n",
                id, nama, getKategori(), stok, harga);
        if (isStokHabis()) System.out.println("   ⚠ STOK HABIS!");
    }
}
