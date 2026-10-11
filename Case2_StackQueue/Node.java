import java.util.Locale;

/**
 * Class Node menyimpan data satu pelanggan/transaksi.
 * Node yang sama dipakai oleh Queue (antrian) dan Stack (riwayat transaksi).
 */
public class Node {
    String nomorAntrian;    // Kode/nomor antrian, contoh: A001
    String namaPelanggan;   // Nama pelanggan
    long totalBelanja;      // Total belanja dalam Rupiah
    Node next;              // Referensi ke node berikutnya

    /** Constructor: membuat node baru berisi data pelanggan. */
    public Node(String nomorAntrian, String namaPelanggan, long totalBelanja) {
        this.nomorAntrian = nomorAntrian;
        this.namaPelanggan = namaPelanggan;
        this.totalBelanja = totalBelanja;
        this.next = null;
    }

    /** Memformat angka ke format Rupiah, contoh: 125000 -> Rp125.000 */
    public static String formatRupiah(long nilai) {
        return "Rp" + String.format(Locale.US, "%,d", nilai).replace(',', '.');
    }

    /** Representasi teks data pelanggan untuk ditampilkan. */
    @Override
    public String toString() {
        return "No: " + nomorAntrian + " | Nama: " + namaPelanggan
                + " | Total: " + formatRupiah(totalBelanja);
    }
}
