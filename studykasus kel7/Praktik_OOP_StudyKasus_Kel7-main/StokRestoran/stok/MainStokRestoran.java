package stok;

import stok.menu.*;
import java.util.Scanner;

public class MainStokRestoran {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Data awal menu restoran
        MenuRestoran[] daftarMenu = new MenuRestoran[] {
            new Makanan("MKN-001", "Nasi Goreng Spesial",   25, 25000, "Nasi"),
            new Makanan("MKN-002", "Mie Ayam Bakso",        20, 20000, "Mie"),
            new Makanan("MKN-003", "Ayam Bakar Madu",       15, 35000, "Lauk"),
            new Makanan("MKN-004", "Gado-Gado",             10, 18000, "Sayur"),
            new Minuman("MIN-001", "Es Teh Manis",          50, 5000,  true),
            new Minuman("MIN-002", "Jus Alpukat",           30, 15000, true),
            new Minuman("MIN-003", "Kopi Hitam",            40, 8000,  false),
            new Minuman("MIN-004", "Air Mineral",           100, 4000, false),
            new Dessert("DST-001", "Es Krim Coklat",        20, 12000, "Coklat"),
            new Dessert("DST-002", "Puding Vanilla",        15, 10000, "Vanilla"),
            new Dessert("DST-003", "Pisang Bakar Strawberry",10, 14000, "Strawberry")
        };

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   SISTEM MANAJEMEN STOK RESTORAN         ║");
        System.out.println("║   Warung Makan Barokah                   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        while (true) {
            System.out.println("\n============ MENU UTAMA ============");
            System.out.println("1. Tampilkan semua menu & stok");
            System.out.println("2. Cari menu berdasarkan ID");
            System.out.println("3. Tampilkan menu per kategori");
            System.out.println("4. Tambah stok menu");
            System.out.println("5. Kurangi stok (penjualan)");
            System.out.println("6. Tampilkan menu stok menipis (≤ 5)");
            System.out.println("7. Tampilkan menu stok habis");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (0-7): ");

            int pilihan = -1;
            try {
                pilihan = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
                continue;
            }

            if (pilihan == 0) {
                System.out.println("Terima kasih! Sampai jumpa.");
                break;
            }

            switch (pilihan) {
                case 1 -> tampilkanSemuaMenu(daftarMenu);
                case 2 -> cariMenuById(scanner, daftarMenu);
                case 3 -> tampilkanPerKategori(scanner, daftarMenu);
                case 4 -> prosesTambahStok(scanner, daftarMenu);
                case 5 -> prosesKurangiStok(scanner, daftarMenu);
                case 6 -> tampilkanStokMenipis(daftarMenu);
                case 7 -> tampilkanStokHabis(daftarMenu);
                default -> System.out.println("Pilihan tidak valid! Masukkan angka 0-7.");
            }
        }

        scanner.close();
    }

    // ─── 1. Tampilkan semua menu ───────────────────────────────────────────────
    private static void tampilkanSemuaMenu(MenuRestoran[] daftarMenu) {
        System.out.println("\n========== DAFTAR MENU & STOK ==========");
        garisPemisah();
        System.out.printf("%-10s | %-25s | %-10s | %-6s | %s%n",
                "ID", "Nama Menu", "Kategori", "Stok", "Harga");
        garisPemisah();
        for (MenuRestoran menu : daftarMenu) {
            menu.tampilkanInfo();
        }
        garisPemisah();
        System.out.printf("Total item menu: %d%n", daftarMenu.length);
    }

    // ─── 2. Cari menu berdasarkan ID ──────────────────────────────────────────
    private static void cariMenuById(Scanner scanner, MenuRestoran[] daftarMenu) {
        System.out.print("Masukkan ID menu yang dicari: ");
        String idCari = scanner.nextLine().trim().toUpperCase();

        MenuRestoran hasil = cariMenu(idCari, daftarMenu);
        if (hasil == null) {
            System.out.println("Menu dengan ID \"" + idCari + "\" tidak ditemukan!");
        } else {
            System.out.println("\n=== DETAIL MENU ===");
            hasil.tampilkanInfo();
        }
    }

    // ─── 3. Tampilkan per kategori ────────────────────────────────────────────
    private static void tampilkanPerKategori(Scanner scanner, MenuRestoran[] daftarMenu) {
        System.out.println("\nKategori yang tersedia:");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Dessert");
        System.out.print("Pilih kategori (1-3): ");

        int pil;
        try {
            pil = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
            return;
        }

        String kategori = switch (pil) {
            case 1 -> "Makanan";
            case 2 -> "Minuman";
            case 3 -> "Dessert";
            default -> null;
        };

        if (kategori == null) {
            System.out.println("Pilihan kategori tidak valid!");
            return;
        }

        System.out.println("\n=== MENU KATEGORI: " + kategori.toUpperCase() + " ===");
        garisPemisah();
        boolean ada = false;
        for (MenuRestoran menu : daftarMenu) {
            if (menu.getKategori().equalsIgnoreCase(kategori)) {
                menu.tampilkanInfo();
                ada = true;
            }
        }
        if (!ada) System.out.println("Tidak ada menu dalam kategori ini.");
        garisPemisah();
    }

    // ─── 4. Tambah stok ───────────────────────────────────────────────────────
    private static void prosesTambahStok(Scanner scanner, MenuRestoran[] daftarMenu) {
        tampilkanSemuaMenu(daftarMenu);
        System.out.print("\nMasukkan ID menu yang ingin ditambah stoknya: ");
        String id = scanner.nextLine().trim().toUpperCase();

        MenuRestoran menu = cariMenu(id, daftarMenu);
        if (menu == null) {
            System.out.println("Menu dengan ID \"" + id + "\" tidak ditemukan!");
            return;
        }

        System.out.printf("Stok saat ini \"%s\": %d%n", menu.getNama(), menu.getStok());
        System.out.print("Masukkan jumlah tambah stok: ");
        try {
            int jumlah = Integer.parseInt(scanner.nextLine().trim());
            menu.tambahStok(jumlah);
        } catch (NumberFormatException e) {
            System.out.println("Input jumlah tidak valid!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ─── 5. Kurangi stok (penjualan) ─────────────────────────────────────────
    private static void prosesKurangiStok(Scanner scanner, MenuRestoran[] daftarMenu) {
        tampilkanSemuaMenu(daftarMenu);
        System.out.print("\nMasukkan ID menu yang terjual: ");
        String id = scanner.nextLine().trim().toUpperCase();

        MenuRestoran menu = cariMenu(id, daftarMenu);
        if (menu == null) {
            System.out.println("Menu dengan ID \"" + id + "\" tidak ditemukan!");
            return;
        }

        if (menu.isStokHabis()) {
            System.out.println("Stok \"" + menu.getNama() + "\" sudah habis, tidak bisa dikurangi!");
            return;
        }

        System.out.printf("Stok saat ini \"%s\": %d%n", menu.getNama(), menu.getStok());
        System.out.print("Masukkan jumlah yang terjual: ");
        try {
            int jumlah = Integer.parseInt(scanner.nextLine().trim());
            boolean berhasil = menu.kurangiStok(jumlah);
            if (berhasil) {
                double total = jumlah * menu.getHarga();
                System.out.printf("Total pendapatan dari transaksi ini: Rp %,.0f%n", total);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input jumlah tidak valid!");
        }
    }

    // ─── 6. Stok menipis ─────────────────────────────────────────────────────
    private static void tampilkanStokMenipis(MenuRestoran[] daftarMenu) {
        System.out.println("\n=== ⚠ MENU DENGAN STOK MENIPIS (≤ 5) ===");
        boolean ada = false;
        for (MenuRestoran menu : daftarMenu) {
            if (menu.getStok() <= 5 && menu.getStok() > 0) {
                menu.tampilkanInfo();
                ada = true;
            }
        }
        if (!ada) System.out.println("Semua stok masih aman.");
    }

    // ─── 7. Stok habis ───────────────────────────────────────────────────────
    private static void tampilkanStokHabis(MenuRestoran[] daftarMenu) {
        System.out.println("\n=== ❌ MENU DENGAN STOK HABIS ===");
        boolean ada = false;
        for (MenuRestoran menu : daftarMenu) {
            if (menu.isStokHabis()) {
                menu.tampilkanInfo();
                ada = true;
            }
        }
        if (!ada) System.out.println("Tidak ada menu yang stoknya habis.");
    }

    // ─── Helper methods ───────────────────────────────────────────────────────
    private static MenuRestoran cariMenu(String id, MenuRestoran[] daftarMenu) {
        for (MenuRestoran menu : daftarMenu) {
            if (menu.getId().equalsIgnoreCase(id)) return menu;
        }
        return null;
    }

    private static void garisPemisah() {
        System.out.println("-".repeat(80));
    }
}
