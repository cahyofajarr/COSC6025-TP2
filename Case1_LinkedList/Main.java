import java.util.Scanner;

/**
 * Program Sistem Data Buku Perpustakaan menggunakan Single Linked List.
 * Menu: Tambah (Push), Hapus (Pop), Cari, Tampilkan Semua, Keluar.
 */
public class Main {
    private static final int MAKS_PANJANG_KODE = 5;  // Batas panjang kodeBuku
    private static final int MINIMAL_BUKU = 5;       // Jumlah data minimal yang disyaratkan

    private static final Scanner scanner = new Scanner(System.in);
    private static final LinkedList daftarBuku = new LinkedList();

    public static void main(String[] args) {
        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();
            String input = bacaBaris("Pilih menu: ");
            if (input == null) break; // Input berakhir (EOF), keluar dengan aman
            System.out.println();

            switch (input) {
                case "1": tambahBuku(); break;
                case "2": hapusBuku();  break;
                case "3": cariBuku();   break;
                case "4": daftarBuku.display(); break;
                case "5":
                    System.out.println("Terima kasih. Program selesai.");
                    berjalan = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Masukkan angka 1-5.");
            }
            System.out.println();
        }
        scanner.close();
    }

    /** Menampilkan menu utama program. */
    private static void tampilkanMenu() {
        System.out.println("===== SISTEM DATA BUKU =====");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Hapus Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Lihat Semua Buku");
        System.out.println("5. Keluar");
    }

    /**
     * Menu 1: Tambah buku (push ke akhir list).
     * Validasi: kode tidak kosong, maksimal 5 karakter, tidak duplikat;
     * judul dan penulis tidak boleh kosong.
     */
    private static void tambahBuku() {
        String kode = bacaKodeBukuBaru();
        if (kode == null) return;

        String judul = bacaTidakKosong("Masukkan Judul: ");
        if (judul == null) return;

        String penulis = bacaTidakKosong("Masukkan Penulis: ");
        if (penulis == null) return;

        daftarBuku.push(kode, judul, penulis);
        System.out.println("Data berhasil ditambahkan!");

        // Pengingat jumlah data minimal sesuai ketentuan soal
        if (daftarBuku.getSize() < MINIMAL_BUKU) {
            int kurang = MINIMAL_BUKU - daftarBuku.getSize();
            System.out.println("Info: Jumlah buku saat ini " + daftarBuku.getSize()
                    + ". Tambahkan " + kurang + " buku lagi untuk memenuhi minimal "
                    + MINIMAL_BUKU + " buku.");
        }
    }

    /** Menu 2: Hapus buku terakhir (pop). */
    private static void hapusBuku() {
        Node dihapus = daftarBuku.pop();
        if (dihapus == null) {
            System.out.println("Tidak ada data untuk dihapus.");
        } else {
            System.out.println("Buku terakhir berhasil dihapus:");
            System.out.println(dihapus);
            System.out.println("Sisa Buku: " + daftarBuku.getSize());
        }
    }

    /** Menu 3: Cari buku berdasarkan kodeBuku. */
    private static void cariBuku() {
        String kode = bacaTidakKosong("Masukkan Kode Buku yang dicari: ");
        if (kode == null) return;

        Node hasil = daftarBuku.search(kode);
        if (hasil == null) {
            System.out.println("Buku tidak ditemukan.");
        } else {
            System.out.println("Buku ditemukan!");
            System.out.println("Kode    : " + hasil.kodeBuku);
            System.out.println("Judul   : " + hasil.judul);
            System.out.println("Penulis : " + hasil.penulis);
        }
    }

    /**
     * Membaca kodeBuku baru dan mengulang input sampai valid:
     * tidak kosong, maksimal 5 karakter, dan belum terdaftar.
     */
    private static String bacaKodeBukuBaru() {
        while (true) {
            String kode = bacaTidakKosong("Masukkan Kode Buku: ");
            if (kode == null) return null;

            if (kode.length() > MAKS_PANJANG_KODE) {
                System.out.println("Kode Buku maksimal " + MAKS_PANJANG_KODE
                        + " karakter. Silakan ulangi.");
            } else if (daftarBuku.search(kode) != null) {
                System.out.println("Kode Buku sudah terdaftar. Gunakan kode lain.");
            } else {
                return kode.toUpperCase(); // Standarisasi kode ke huruf kapital
            }
        }
    }

    /** Membaca input yang tidak boleh kosong; mengulang sampai terisi. */
    private static String bacaTidakKosong(String prompt) {
        while (true) {
            String nilai = bacaBaris(prompt);
            if (nilai == null) return null;
            if (!nilai.isEmpty()) return nilai;
            System.out.println("Input tidak boleh kosong. Silakan ulangi.");
        }
    }

    /** Membaca satu baris input (sudah di-trim). Mengembalikan null jika input habis. */
    private static String bacaBaris(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) return null;
        return scanner.nextLine().trim();
    }
}
