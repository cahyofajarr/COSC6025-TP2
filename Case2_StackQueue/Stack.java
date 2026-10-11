/**
 * Class Stack (riwayat transaksi) berbasis Linked List dengan prinsip LIFO
 * (Last In First Out): transaksi terbaru selalu berada di atas (top).
 */
public class Stack {
    private Node top;
    private int size;

    /** Constructor: membuat stack kosong. */
    public Stack() {
        this.top = null;
        this.size = 0;
    }

    /** Mengecek apakah stack kosong. */
    public boolean isEmpty() {
        return top == null;
    }

    /** Mengembalikan jumlah transaksi dalam stack. */
    public int getSize() {
        return size;
    }

    /**
     * PUSH: menyimpan transaksi di atas stack (top), O(1).
     * Node baru menunjuk ke top lama, lalu menjadi top baru.
     */
    public void push(Node transaksi) {
        transaksi.next = top;
        top = transaksi;
        size++;
    }

    /** PEEK: melihat transaksi teratas (terbaru) tanpa menghapusnya. */
    public Node peek() {
        return top;
    }

    /** Mengecek apakah nomor antrian sudah tercatat di riwayat transaksi. */
    public boolean contains(String nomorAntrian) {
        Node current = top;
        while (current != null) {
            if (current.nomorAntrian.equalsIgnoreCase(nomorAntrian)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * DISPLAY: menampilkan riwayat dari top (terbaru) ke bawah (terlama),
     * beserta jumlah transaksi dan total pendapatan.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }

        System.out.println("Riwayat Transaksi (terbaru -> terlama):");
        Node current = top;
        int nomor = 1;
        long totalPendapatan = 0;
        while (current != null) {
            String penanda = (current == top) ? "  <- terbaru" : "";
            System.out.println(nomor + ". " + current + penanda);
            totalPendapatan += current.totalBelanja;
            current = current.next;
            nomor++;
        }
        System.out.println("Jumlah Transaksi : " + size);
        System.out.println("Total Pendapatan : " + Node.formatRupiah(totalPendapatan));
    }
}
