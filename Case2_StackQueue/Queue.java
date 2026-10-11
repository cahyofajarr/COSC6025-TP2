/**
 * Class Queue (antrian pelanggan) berbasis Linked List dengan prinsip FIFO
 * (First In First Out): pelanggan yang datang pertama dilayani pertama.
 * - front : pelanggan terdepan (berikutnya dilayani)
 * - rear  : pelanggan paling belakang (terakhir masuk)
 * Kapasitas antrian dibatasi (bounded queue) sesuai ketentuan jumlah data <= 5.
 */
public class Queue {
    private Node front;
    private Node rear;
    private int size;
    private final int capacity;

    /** Constructor: membuat antrian kosong dengan kapasitas tertentu. */
    public Queue(int capacity) {
        this.front = null;
        this.rear = null;
        this.size = 0;
        this.capacity = capacity;
    }

    /** Mengecek apakah antrian kosong. */
    public boolean isEmpty() {
        return front == null;
    }

    /** Mengecek apakah antrian sudah mencapai kapasitas maksimal. */
    public boolean isFull() {
        return size >= capacity;
    }

    /** Mengembalikan jumlah pelanggan dalam antrian. */
    public int getSize() {
        return size;
    }

    /** Mengembalikan kapasitas maksimal antrian. */
    public int getCapacity() {
        return capacity;
    }

    /**
     * ENQUEUE: menambahkan pelanggan di belakang antrian (rear), O(1).
     * @return true jika berhasil, false jika antrian penuh
     */
    public boolean enqueue(String nomorAntrian, String namaPelanggan, long totalBelanja) {
        if (isFull()) {
            return false;
        }

        Node newNode = new Node(nomorAntrian, namaPelanggan, totalBelanja);
        if (isEmpty()) {
            // Antrian kosong: node baru menjadi front sekaligus rear
            front = newNode;
            rear = newNode;
        } else {
            // Sambungkan rear lama ke node baru, lalu geser rear
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        return true;
    }

    /**
     * DEQUEUE: mengeluarkan pelanggan terdepan (front), O(1).
     * @return node pelanggan yang dikeluarkan, atau null jika antrian kosong
     */
    public Node dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node served = front;
        front = front.next;   // Geser front ke pelanggan berikutnya
        if (front == null) {
            rear = null;      // Antrian menjadi kosong
        }
        served.next = null;   // Lepaskan node dari antrian
        size--;
        return served;
    }

    /** Mengecek apakah nomor antrian sudah ada di dalam antrian. */
    public boolean contains(String nomorAntrian) {
        Node current = front;
        while (current != null) {
            if (current.nomorAntrian.equalsIgnoreCase(nomorAntrian)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** DISPLAY: menampilkan antrian dari depan (front) ke belakang (rear). */
    public void display() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }

        System.out.println("Antrian Saat Ini (depan -> belakang):");
        Node current = front;
        int posisi = 1;
        while (current != null) {
            String penanda = (current == front) ? "  <- berikutnya dilayani" : "";
            System.out.println(posisi + ". " + current + penanda);
            current = current.next;
            posisi++;
        }
        System.out.println("Jumlah Antrian: " + size + "/" + capacity);
    }
}
