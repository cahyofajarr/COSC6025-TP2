/**
 * Class Node merepresentasikan satu simpul (node) pada Single Linked List.
 * Setiap node menyimpan data satu buku dan referensi ke node berikutnya.
 */
public class Node {
    String kodeBuku;   // Kode unik buku (maksimal 5 karakter)
    String judul;      // Judul buku
    String penulis;    // Nama penulis buku
    Node next;         // Penunjuk ke node berikutnya (null jika node terakhir)

    /**
     * Constructor: membuat node baru berisi data buku.
     * Nilai next otomatis null karena node baru belum terhubung ke node lain.
     */
    public Node(String kodeBuku, String judul, String penulis) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.next = null;
    }

    /** Mengembalikan representasi teks dari data buku sesuai format output. */
    @Override
    public String toString() {
        return "Kode: " + kodeBuku + " | Judul: " + judul + " | Penulis: " + penulis;
    }
}
