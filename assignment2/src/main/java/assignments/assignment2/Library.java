package assignments.assignment2;

import java.util.Scanner;

public class Library {
    private Scanner input = new Scanner(System.in);

    private Member[] members;
    private Book[] books;
    private Category[] categories;

    public static void main(String[] args) {
        Library program = new Library();
        program.menu();
    }

    public void menu() {
        int command = 0;
        boolean hasChosenExit = false;
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Peminjaman");
            System.out.println("5. Pengembalian");
            System.out.println("6. Pembayaran Denda");
            System.out.println("7. Detail Anggota");
            System.out.println("8. 3 Peringkat Pertama");
            System.out.println("99. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                // TODO
            } else if (command == 2) {
                // TODO
            } else if (command == 3) {
                // TODO
            } else if (command == 4) {
                // TODO
            } else if (command == 5) {
                // TODO
            } else if (command == 6) {
                // TODO
            } else if (command == 7) {
                // TODO
            } else if (command == 8) {
                // TODO
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }

        input.close();
    }
}
