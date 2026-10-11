import java.util.Scanner;

/**
 * Program Sistem Kasir Toko.
 * - Queue : antrian pelanggan yang menunggu dilayani (FIFO)
 * - Stack : riwayat transaksi pelanggan yang sudah dilayani (LIFO)
 */
public class Main {
    private static final int KAPASITAS_ANTRIAN = 5; // Validasi jumlah data <= 5

    private static final Scanner scanner = new Scanner(System.in);
    private static final Queue antrian = new Queue(KAPASITAS_ANTRIAN);
    private static final Stack riwayat = new Stack();

    public static void main(String[] args) {
        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();
            String input = bacaBaris("Pilih menu: ");
            if (input == null) break; // Input berakhir (EOF), keluar dengan aman
            System.out.println();

            switch (input) {
                case "1": tambahAntrian();     break;
                case "2": layaniPelanggan();   break;
                case "3": antrian.display();   break;
                case "4": riwayat.display();   break;
                case "5":
                    System.out.println("Terima kasih. Program kasir selesai.");
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
        System.out.println("=== SISTEM KASIR TOKO ===");
        System.out.println("1. Tambah Antrian");
        System.out.println("2. Layani Pelanggan");
        System.out.println("3. Tampilkan Antrian");
        System.out.println("4. Lihat Riwayat Transaksi");
        System.out.println("5. Keluar");
    }

    /**
     * Menu 1: Tambah pelanggan ke antrian (enqueue).
     * Kapasitas dicek lebih dulu agar pengguna tidak mengisi data sia-sia.
     */
    private static void tambahAntrian() {
        if (antrian.isFull()) {
            System.out.println("Antrian penuh! Maksimal " + KAPASITAS_ANTRIAN
                    + " pelanggan. Layani pelanggan terlebih dahulu.");
            return;
        }

        String nomor = bacaNomorAntrianBaru();
        if (nomor == null) return;

        String nama = bacaTidakKosong("Masukkan Nama Pelanggan: ");
        if (nama == null) return;

        Long total = bacaTotalBelanja();
        if (total == null) return;

        antrian.enqueue(nomor, nama, total);
        System.out.println("Data pelanggan ditambahkan ke antrian!");
        System.out.println("Jumlah Antrian: " + antrian.getSize() + "/" + KAPASITAS_ANTRIAN);
    }

    /** Menu 2: Layani pelanggan terdepan (dequeue), lalu simpan ke riwayat (push). */
    private static void layaniPelanggan() {
        Node pelanggan = antrian.dequeue();
        if (pelanggan == null) {
            System.out.println("Antrian kosong. Tidak ada pelanggan untuk dilayani.");
            return;
        }

        System.out.println("Melayani pelanggan " + pelanggan.nomorAntrian
                + " (" + pelanggan.namaPelanggan + ")");
        System.out.println("Total Belanja: " + Node.formatRupiah(pelanggan.totalBelanja));

        riwayat.push(pelanggan);
        System.out.println("Transaksi disimpan ke riwayat.");
        System.out.println("Sisa Antrian: " + antrian.getSize());
    }

    /**
     * Membaca nomor antrian baru sampai valid: tidak kosong dan
     * belum dipakai, baik di antrian maupun di riwayat transaksi.
     */
    private static String bacaNomorAntrianBaru() {
        while (true) {
            String nomor = bacaTidakKosong("Masukkan Nomor Antrian: ");
            if (nomor == null) return null;

            if (antrian.contains(nomor) || riwayat.contains(nomor)) {
                System.out.println("Nomor antrian sudah digunakan. Gunakan nomor lain.");
            } else {
                return nomor.toUpperCase(); // Standarisasi ke huruf kapital
            }
        }
    }

    /** Membaca total belanja sampai valid: berupa angka bulat dan lebih dari 0. */
    private static Long bacaTotalBelanja() {
        while (true) {
            String input = bacaTidakKosong("Masukkan Total Belanja: ");
            if (input == null) return null;

            try {
                long total = Long.parseLong(input);
                if (total > 0) {
                    return total;
                }
                System.out.println("Total belanja harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Total belanja harus berupa angka bulat (contoh: 125000).");
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
