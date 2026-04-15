## Kelompok 7
# Sistem Manajemen Stok Restoran "Warung Makan Barokah"

## 🎯 Tema
**Sistem Manajemen Stok Restoran**  
Mengelola berbagai jenis menu (Makanan, Minuman, dan Dessert) beserta stoknya secara dinamis dengan memanfaatkan konsep OOP.

---

## 🎯 Tujuan Pembelajaran
- Memahami konsep **Abstract Class** dan **Method Abstract**
- Mengimplementasikan **Inheritance** antar kelas menu
- Menggunakan **Polymorphism** melalui overriding method `tampilkanInfo()`, `getKategori()`, dan `getKeterangan()`
- Menerapkan **Encapsulation** dengan setter yang memiliki validasi
- Membuat program berbasis menu (menu-driven program)

---

## 📁 Struktur File

```bash
StokRestoran/
└── stok/
    ├── MainStokRestoran.java
    └── menu/
        ├── MenuRestoran.java
        ├── Makanan.java
        ├── Minuman.java
        └── Dessert.java
```
---

## 🏗️ Penjelasan Class

### 1. `MenuRestoran.java` (Abstract Class)
- **Superclass** untuk semua jenis menu
- Berisi atribut protected: `id`, `nama`, `stok`, `harga`
- Memiliki setter dengan **validasi** (nama tidak boleh kosong, stok tidak boleh negatif, harga > 0)
- Method umum: `tambahStok()`, `kurangiStok()`, `isStokHabis()`
- Method abstract wajib di-override:
  - `getKategori()`
  - `getKeterangan()`

### 2. Subclass yang Meng-extend `MenuRestoran`

| Class       | Atribut Tambahan       | Keterangan Tambahan                  |
|-------------|------------------------|--------------------------------------|
| `Makanan`   | `jenisHidangan`        | Nasi, Mie, Lauk, Sayur, dll         |
| `Minuman`   | `dingin` (boolean)     | Dingin/Ek atau Panas                 |
| `Dessert`   | `rasa`                 | Coklat, Vanilla, Strawberry, dll    |

Semua subclass meng-override:
- `getKategori()`
- `getKeterangan()`
- `tampilkanInfo()` (menambahkan detail spesifik)

---

## ✨ Fitur yang Tersedia

1. **Tampilkan semua menu & stok**
2. **Cari menu berdasarkan ID**
3. **Tampilkan menu per kategori** (Makanan / Minuman / Dessert)
4. **Tambah stok** menu tertentu
5. **Kurangi stok** (simulasi penjualan) + hitung pendapatan
6. **Tampilkan menu stok menipis** (stok ≤ 5)
7. **Tampilkan menu stok habis**

---

## 🚀 Cara Menjalankan Program

### Persiapan
1. Pastikan sudah terinstall **JDK 8** atau lebih baru
2. Compile semua file Java

### Cara Compile & Run

```bash
# Masuk ke folder stok
cd StokRestoran/stok

# Compile semua file
Praktik_OOP_StudyKasus_Kel7\StokRestoran\StokRestoran\stok

# Jalankan program
java MainStokRestoran.java
