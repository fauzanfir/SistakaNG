package assignments.assignment3;

import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Kategori;
import assignments.assignment3.pengguna.Anggota;
import assignments.assignment3.pengguna.CanBorrow;
import assignments.assignment3.pengguna.Dosen;
import assignments.assignment3.pengguna.Mahasiswa;
import assignments.assignment3.pengguna.Pengguna;
import assignments.assignment3.pengguna.Staf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;

public class SistakaNG {
    public static ArrayList<Staf> daftarStaf = new ArrayList<Staf>();
    public static ArrayList<Anggota> daftarAnggota = new ArrayList<Anggota>();
    public static ArrayList<Buku> daftarBuku = new ArrayList<Buku>();
    public static ArrayList<Kategori> daftarKategori = new ArrayList<Kategori>();
    public static Pengguna penggunaLoggedIn;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Start - Register Staf...");
        registerStaf();
        System.out.println("Done - Register Staf...\n");
        menu();
        input.close();
    }

    // Method ini digunakan untuk mendaftarkan staf-staf ketika program dijalankan
    private static void registerStaf() {
        String[] listNama = new String[]{"Dek Depe", "Dek DePram", "Dek Sofita", "Winter", "Boo"};

        for (int i = 0; i < listNama.length; i++) {
            Staf staf = new Staf(listNama[i]);

            daftarStaf.add(staf);
            System.out.println("Berhasil menambahkan staf dengan data:");
            System.out.println(staf);
        }
    }

    // Method ini digunakan untuk mencetak menu utama dari SistakaNG
    public static void menu() {
        boolean hasChosenExit = false;
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            int command = 0;
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                menuLogin();
            } else if (command == 2) {
                System.out.print("Terima kasih telah menggunakan SistakaNG. Sampai jumpa di lain kesempatan!");
                hasChosenExit = true;
            } else {
                System.out.print("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu login
    public static void menuLogin() {
        boolean isLoginSuccess = false;
        while (!isLoginSuccess) {
            System.out.println("Masukkan ID Anda untuk login ke sistem");
            System.out.print("ID: ");
            String id = input.nextLine();
            for(Pengguna pengguna : daftarStaf){
                if(pengguna.getId().equals(id)){
                    penggunaLoggedIn = pengguna;
                    isLoginSuccess = true;
                }
            }
            for(Pengguna pengguna : daftarAnggota){
                if(pengguna.getId().equals(id)){
                    penggunaLoggedIn = pengguna;
                    isLoginSuccess = true;
                }
            }
            if(!isLoginSuccess){
                System.out.printf("Pengguna dengan ID %s tidak ditemukan", id);
            }
        }
        System.out.printf("Halo, %s! Selamat datang di SistakaNG", penggunaLoggedIn.getName());
        showMenu();
    }

    // Method ini digunakan untuk mencetak menu yang dapat diakses berdasarkan jenis penggunaLoggedIn
    private static void showMenu() {
        if (penggunaLoggedIn instanceof Staf) {
            showMenuStaf();
        } else {
            showMenuAnggota();
        }
    }

    // Method ini digunakan untuk mencetak menu staf dari SistakaNG
    private static void showMenuStaf() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            System.out.println("\n================ Menu Staf ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. 3 Peringkat Pertama");
            System.out.println("6. Detail Anggota");
            System.out.println("7. Daftar Peminjam Buku");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                // TODO: Implementasikan menu-nya
                System.out.println("---------- Tambah Anggota ----------");
                System.out.print("Tipe Anggota: ");
                String tipe = input.nextLine();
                if(tipe.equals("Mahasiswa")){
                    System.out.print("Nama: ");
                    String nama = input.nextLine();
                    System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
                    String progStud = input.nextLine();
                    System.out.print("Angkatan: ");
                    String angkatan = input.nextLine();
                    System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
                    String tanggalLahir = input.nextLine();
                    Mahasiswa mhs = new Mahasiswa(nama, tanggalLahir, progStud, angkatan);
                    String ID = mhs.getId();

                    if (ID.length() == 13){   
                        daftarAnggota.add(mhs);
                        System.out.printf("Berhasil menambahkan mahasiswa dengan data:\n");
                        System.out.print(mhs);
                    }
                    else{
                        System.out.print(ID);       // Jika ID tidak benar, maka akan mengeluarkan handle pada generateId
                    }
                }
                else if(tipe.equals("Dosen")){
                    System.out.print("Nama: ");
                    String nama = input.nextLine();
                    Dosen dosen = new Dosen(nama);
                    daftarAnggota.add(dosen);
                    System.out.printf("Berhasil menambahkan dosen dengan data:\n");
                    System.out.print(dosen);
                }
                else{
                    System.out.printf("Tipe Anggota %s tidak valid!", tipe);
                }

            } else if (command == 2) {
                System.out.println("---------- Tambah Kategori ----------");                
                System.out.print("Nama Kategori: ");
                String nama = input.nextLine();
                System.out.print("Poin: ");
                int point = input.nextInt();
                input.nextLine();
                boolean ada = false;
                for(Kategori kat : daftarKategori){
                    if(nama.equals(kat.getName())){
                        ada = true;
                    }
                }
                if(ada){
                    System.out.printf("Kategori %s sudah pernah ditambahkan", nama);
                }
                else{
                    Kategori ktg = new Kategori(nama, point);
                    daftarKategori.add(ktg);
                    System.out.printf("Kategori %s dengan poin %d berhasil ditambahkan", ktg.getName(), ktg.getPoint());
                }

            } else if (command == 3) {
                System.out.println("---------- Tambah Buku ----------");
                System.out.print("Judul : ");
                String judul = input.nextLine();
                System.out.print("Penulis: ");
                String penulis = input.nextLine();
                System.out.print("Penerbit: ");
                String penerbit = input.nextLine();
                System.out.print("Kategori: ");
                String namaKategori = input.nextLine();
                System.out.print("Stok: ");
                int stok = input.nextInt();
                input.nextLine();
                boolean adaKat = false;
                boolean adaBuku = false;
                Kategori kategori = null;

                for(Kategori kat : daftarKategori){
                    if(kat.getName().toLowerCase().equals(namaKategori.toLowerCase())){
                        adaKat = true;
                        kategori = kat;
                    }
                }
                for(Buku buku : daftarBuku){
                    if(buku.getName().toLowerCase().equals(judul.toLowerCase()) &&
                    buku.getAuthor().toLowerCase().equals(penulis.toLowerCase())){
                        adaBuku = true;
                    }
                }

                if(!adaKat){
                    System.out.printf("Kategori %s tidak ditemukan", namaKategori);
                }
                else if (stok <= 0){
                    System.out.print("Stok harus lebih dari 0");
                }
                else if(adaBuku){
                    System.out.printf("Buku %s oleh %s sudah pernah ditambahkan", judul, penulis);
                }
                else{       // Jika buku belum ada, kategori sesuai, dan stok lebih dari 0
                    Buku buku = new Buku(judul, penulis, penerbit, kategori, stok);
                    System.out.printf("Buku %s oleh %s berhasil ditambahkan", buku.getName(), buku.getAuthor());
                    // Memasukkan buku pada list buku
                    daftarBuku.add(buku);
                }

            } else if (command == 4) {
                System.out.println("---------- Hapus Buku ----------");
                System.out.print("Judul: ");
                String judul = input.nextLine();
                System.out.print("Penulis: ");
                String penulis = input.nextLine();
                boolean adaBuku = false;
                Buku iniBuku = null;

                for(Buku buku : daftarBuku){        // Asumsi : case insensitive
                    if(buku.getName().toLowerCase().equals(judul.toLowerCase()) &&
                    buku.getAuthor().toLowerCase().equals(penulis.toLowerCase())){
                        adaBuku = true;
                        iniBuku = buku;
                    }
                }
                if(!adaBuku){
                    System.out.printf("Buku %s oleh %s tidak ditemukan", judul, penulis);
                }
                else if(iniBuku.getStokAwal() != iniBuku.getStok()){
                    System.out.printf("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam", judul, penulis);
                }
                else{
                    System.out.printf("Buku %s oleh %s berhasil dihapus", iniBuku.getName(), iniBuku.getAuthor());
                    daftarBuku.remove(iniBuku);
                }

            } else if (command == 5) {
                System.out.println("---------- Peringkat Anggota ----------");
                if(daftarAnggota.size() == 0){
                    System.out.print("Belum ada anggota yang terdaftar pada sistem");
                }
                else{
                    Comparator<Anggota> byPoint = Comparator.comparing(Anggota::getPoint).reversed();
                    //Comparator<Member> byName = Comparator.comparing(Member::getName);
                    //Arrays.sort(members, byPoint.thenComparing(byName));
                    //Comparator<Anggota> byName = Comparator.
                    Collections.sort(daftarAnggota, byPoint.thenComparing(Anggota.banding));
 
                    for(int i = 0;i < daftarAnggota.size();i++){
                        if(i == 3){
                            break;
                        }
                        System.out.printf("----------------- %d -----------------\n", i+1);
                        System.out.print(daftarAnggota.get(i));
                        if(i != 2 || i != daftarAnggota.size()-1){
                            System.out.println();
                        }
                    }
                }

            } else if (command == 6) {
                System.out.println("---------- Detail Anggota ----------");
                System.out.print("ID Anggota: ");
                String iniId = input.nextLine();
                boolean adaAnggota = false;
                Anggota anggota = null;
                for(Anggota iniAnggota : daftarAnggota){
                    if(iniAnggota.getId().equals(iniId)){
                        adaAnggota = true;
                        anggota = iniAnggota;
                    }
                }
                if(!adaAnggota){
                    System.out.printf("Anggota dengan ID %s tidak ditemukan", iniId);
                }
                else{
                    anggota.detail();
                }

            } else if (command == 7) {
                System.out.println("---------- Daftar Peminjam Buku ----------");
                System.out.print("Judul : ");
                String judul = input.nextLine();
                System.out.print("Penulis: ");
                String penulis = input.nextLine();
                boolean adaBuku = false;
                Buku iniBuku = null;
                
                for(Buku buku : daftarBuku){        // Asumsi : case insensitive
                    if(buku.getName().toLowerCase().equals(judul.toLowerCase()) &&
                    buku.getAuthor().toLowerCase().equals(penulis.toLowerCase())){
                        adaBuku = true;
                        iniBuku = buku;
                    }
                }

                if(!adaBuku){
                    System.out.printf("Buku %s oleh %s tidak ditemukan", judul, penulis);
                }
                else{
                    System.out.println(iniBuku);
                    System.out.print("---------- Daftar Peminjam ----------");
                    if(iniBuku.getDaftarPinjam().size() == 0){
                        System.out.printf("Belum ada anggota yang meminjam buku %s", iniBuku.getName());
                    }
                    else{
                        int i = 1;
                        for(CanBorrow iniAnggota : iniBuku.getDaftarPinjam()){
                            System.out.printf("\n----------------- %d -----------------\n", i);
                            System.out.print((Anggota)iniAnggota);
                            i++;
                        } 
                    }
                }

            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;

            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu anggota dari SistakaNG
    private static void showMenuAnggota() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            System.out.println("\n================ Menu Anggota ================\n");
            System.out.println("1. Peminjaman");
            System.out.println("2. Pengembalian");
            System.out.println("3. Pembayaran Denda");
            System.out.println("4. Detail Anggota");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                System.out.println("---------- Peminjaman Buku ----------");
                System.out.print("Judul Buku: ");
                String judul = input.nextLine();
                System.out.print("Penulis Buku: ");
                String penulis = input.nextLine();
                System.out.print("Tanggal Peminjaman: ");
                String tgl = input.nextLine();
                boolean adaBuku = false;
                Buku buku = null;

                for(Buku iniBuku : daftarBuku){
                    if(iniBuku.getName().equals(judul) && iniBuku.getAuthor().equals(penulis)){
                        adaBuku = true;
                        buku = iniBuku;
                    }
                }

                if(!adaBuku){
                    System.out.printf("Buku %s oleh %s tidak ditemukan", judul, penulis);
                }
                else if(buku.getStok() < 1){
                    System.out.printf("Buku %s oleh %s tidak tersedia", buku.getName(), buku.getAuthor());
                }
                else{
                    System.out.print(((Anggota)penggunaLoggedIn).pinjam(buku, tgl));
                }

            } else if (command == 2) {
                System.out.println("---------- Pengembalian Buku ----------");
                System.out.print("Judul Buku: ");
                String judul = input.nextLine();
                System.out.print("Penulis Buku: ");
                String penulis = input.nextLine();
                System.out.print("Tanggal Pengembalian: ");
                String tgl = input.nextLine();
                boolean adaBuku = false;
                Buku buku = null;

                for(Buku iniBuku : daftarBuku){
                    if(iniBuku.getName().equals(judul) && iniBuku.getAuthor().equals(penulis)){
                        adaBuku = true;
                        buku = iniBuku;
                    }
                }
                if(!adaBuku){
                    System.out.printf("Buku %s oleh %s tidak ditemukan", judul, penulis);
                }
                else{
                    System.out.print(((Anggota)penggunaLoggedIn).kembali(buku, tgl));
                }

            } else if (command == 3) {
                System.out.println("---------- Pembayaran Denda ----------");
                System.out.print("Jumlah: ");
                Long jumlah = input.nextLong();
                input.nextLine();
                System.out.print(((Anggota)penggunaLoggedIn).bayarDenda(jumlah));

            } else if (command == 4) {
                ((Anggota)penggunaLoggedIn).detail();

            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;

            } else {
                System.out.println("Menu tidak dikenal!");

            }
            System.out.println();
        }
    }
}
