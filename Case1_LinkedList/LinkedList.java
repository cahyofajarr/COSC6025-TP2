/**
 * Class LinkedList mengimplementasikan Single Linked List untuk menyimpan data buku.
 * - head : node pertama (data yang paling awal diinput)
 * - tail : node terakhir (data yang paling akhir diinput)
 * - size : jumlah node di dalam list
 */
public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    /** Constructor: membuat list kosong. */
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /** Mengecek apakah list kosong. */
    public boolean isEmpty() {
        return head == null;
    }

    /** Mengembalikan jumlah buku di dalam list. */
    public int getSize() {
        return size;
    }

    /**
     * PUSH: menambahkan buku baru di AKHIR list.
     * Karena menyimpan referensi tail, operasi ini berjalan O(1).
     */
    public void push(String kodeBuku, String judul, String penulis) {
        Node newNode = new Node(kodeBuku, judul, penulis);

        if (isEmpty()) {
            // List kosong: node baru menjadi head sekaligus tail
            head = newNode;
            tail = newNode;
        } else {
            // Sambungkan tail lama ke node baru, lalu geser tail
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * POP: menghapus buku TERAKHIR dari list.
     * Pada Single Linked List, kita harus menelusuri list untuk menemukan
     * node sebelum tail, sehingga operasi ini berjalan O(n).
     *
     * @return node yang dihapus, atau null jika list kosong
     */
    public Node pop() {
        if (isEmpty()) {
            return null;
        }

        Node removed = tail;

        if (head == tail) {
            // Hanya ada satu node: list menjadi kosong
            head = null;
            tail = null;
        } else {
            // Cari node sebelum tail (node kedua dari belakang)
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null; // Putuskan hubungan ke tail lama
            tail = current;      // Node tersebut menjadi tail baru
        }
        size--;
        return removed;
    }

    /**
     * SEARCH: mencari buku berdasarkan kodeBuku (linear search, O(n)).
     * Pencarian tidak membedakan huruf besar/kecil.
     *
     * @return node yang ditemukan, atau null jika tidak ada
     */
    public Node search(String kodeBuku) {
        Node current = head;
        while (current != null) {
            if (current.kodeBuku.equalsIgnoreCase(kodeBuku)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * DISPLAY: menampilkan seluruh buku dari head ke tail (urutan input),
     * lalu menampilkan total jumlah buku.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Daftar buku masih kosong.");
            System.out.println("Total Buku: 0");
            return;
        }

        System.out.println("Daftar Buku:");
        Node current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
        System.out.println("Total Buku: " + size);
    }
}
