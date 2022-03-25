// Mengimport class pada library java
package assignments.assignment2;
import java.util.Arrays;
import java.util.Comparator;
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

    public void menu(){
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
                System.out.println("---------- Tambah Anggota ----------");
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
                String progStud = input.nextLine();
                System.out.print("Angkatan: ");
                String angkatan = input.nextLine();
                System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
                String tanggalLahir = input.nextLine();
                String ID = IdGenerator.generateId(progStud, angkatan, tanggalLahir);   // Membuat ID member
                if (ID.length() == 13){                
                    Member member = new Member(nama, progStud, angkatan, tanggalLahir, ID); // Membuat object member
                    if (members == null){           // Jika belum ada member terdaftar
                        members = new Member[1];
                        members[0] = member;
                    }
                    else{
                        Member temp[] = new Member[members.length+1];       // Menambahkan member
                        for(int i=0; i<members.length;i++){
                            temp[i] = members[i];
                        }
                        temp[members.length] = member;
                        members = temp.clone();
                    }
                    System.out.println("Member Dek Depe berhasil ditambahkan dengan data:");
                    System.out.printf("ID Anggota: %s", ID);
                    System.out.printf("\nNama Anggota: %s", nama);
                    System.out.printf("\nTotal Point: %d", member.getPoint());
                    System.out.printf("\nDenda: %o\n", member.getFine());
                }
                else{
                    System.out.print(ID);       // Jika ID tidak benar, maka akan mengeluarkan handle pada generateId
                }
                
            } else if (command == 2) {
                System.out.println("---------- Tambah Kategori ----------");                
                System.out.print("Nama Kategori: ");
                String nama = input.nextLine();
                System.out.print("Point: ");
                int point = input.nextInt();
                input.nextLine();
                Category ktg = new Category(nama, point);           // Membuat object kategori
                if(categories == null){             // Jika belum ada kategori yang ditambahkan
                    categories = new Category[1];
                    categories[0] = ktg;
                    System.out.printf("Kategori %s dengan %d point berhasil ditambahkan\n",ktg.getName(), ktg.getPoint());
                }
                else{
                    boolean ada = false;        // Inisiasi jika kategori belum pernah ditambahkan
                    for(Category kategori: categories){
                        if(kategori.getName().toLowerCase().equals(nama.toLowerCase())){
                            ada = true;         // Jika sudah ada kategori
                            System.out.printf("Kategori %s sudah pernah ditambahkan\n", kategori.getName());
                            break;
                        }
                    }
                    if(!ada){           // Jika belum pernah ditambahkan
                        Category temp[] = new Category[categories.length+1];
                        for(int i=0; i<categories.length;i++){
                            temp[i] = categories[i];
                        }
                        temp[categories.length] = ktg;
                        categories = temp.clone();
                    }
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
                boolean adaKat = false;
                Category kategori = null;
                if (categories == null){        // Jika belum ada kategori yang didaftarkan
                    adaKat = false;     
                }
                else{
                    for(Category ktg : categories){         // Jika kategori sesuai dengan yang diinput
                        if(ktg.getName().toLowerCase().equals(namaKategori.toLowerCase())){
                            adaKat = true;
                            kategori = ktg;
                        }
                    }
                }   
                System.out.print("Stok: ");
                int stok = input.nextInt();
                input.nextLine();
                boolean adaBuku = false;
                if(books == null){              // Jika belum ada buku yang ditambahkan
                    books = new Book[1];
                }
                for(Book buku : books){
                    if(buku == null){
                        adaBuku = false;
                    }
                    else if(buku.getName().toLowerCase().equals(judul.toLowerCase())){  
                        adaBuku = true;                     // Jika buku sudah pernah didaftarkan
                    }
                }

                if (adaBuku){
                    System.out.printf("Buku %s oleh %s sudah pernah ditambahkan\n", judul, penulis);
                }
                else if(!adaKat){
                    System.out.printf("Kategori %s tidak ditemukan\n", namaKategori);
                }
                else if (stok == 0){
                    System.out.println("Stok harus lebih dari 0\n");
                }
                else{       // Jika buku belum ada, kategori sesuai, dan stok lebih dari 0
                    Book buku = new Book(judul, penulis, penerbit, kategori, stok);
                    System.out.printf("Buku %s oleh %s berhasil ditambahkan\n", buku, buku.getAuthor());
                    // Memasukkan buku pada list buku
                    if(books[0] == null){
                        books[0] = buku;        
                    }   
                    else{
                        Book[] temp = new Book[books.length+1];
                        for(int i=0;i<books.length;i++){
                            temp[i] = books[i];
                        }
                        temp[books.length] = buku;
                        books = temp.clone();
                    }
                }

            } else if (command == 4) {
                System.out.println("---------- Peminjaman Buku ----------");
                System.out.print("ID Anggota: ");
                String ID = input.nextLine();
                System.out.print("Judul Buku: ");
                String namaBuku = input.nextLine();
                System.out.print("Penulis Buku: ");
                String penerbit = input.nextLine();
                System.out.print("Tanggal peminjaman: ");
                String tanggal = input.nextLine();
                // Menginisiasi untuk keterangan ada atau tidak
                boolean adaID = false;
                boolean adaBuku = false;
                boolean sudahPinjam = false;
                Member person = null;
                Book book = null;
                if(members == null){
                    adaID = false;
                }
                else{
                    for(Member member : members){       // Jika ada member yang sesuai
                        if(member.getID().equals(ID)){
                            adaID = true;
                            person = member;
                            break;
                        }
                    }
                }
                if(books == null){      
                    adaBuku = false;
                }
                else{
                    for(Book buku : books){     // Jika ada buku yang sesuai
                        if(buku.getName().equals(namaBuku)){
                            adaBuku = true;
                            book = buku;
                            break;
                        }
                    }
                }
                if(person == null || person.getPinjam() == null){     
                    sudahPinjam = false;
                }
                else{           // Jika member sudah pernah meminjam
                    for(BookLoan bukuPinjam : person.getPinjam()){
                        if (bukuPinjam == null){
                            sudahPinjam = false;
                            break;
                        }
                        if(bukuPinjam.getBook().equals(namaBuku)){
                            sudahPinjam = true;     // Jika buku yang dipinjam sedang dipinjam
                        }
                    }
                }
                if(!adaID){
                    System.out.printf("Anggota dengan ID %s tidak ditemukan\n", ID);
                }
                else if(!adaBuku){
                    System.out.printf("Buku %s oleh %s tidak ditemukan\n", namaBuku, penerbit);
                }
                else if(book.getStock() < 1){
                    System.out.printf("Buku %s tidak %s\n", book, book.getAuthor());
                }
                else if(person.getPinjam().length == 3){
                    System.out.printf("Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal\n");
                }
                else if(person.getFine() >= 5000){
                    System.out.printf("Denda lebih dari Rp 5000\n");
                }
                else if(sudahPinjam){
                    System.out.printf("Buku %s oleh %s sedang dipinjam\n", book, book.getAuthor());
                }
                else{
                    person.pinjam(book, tanggal);       // Memanggil method untuk meminjam buku
                    System.out.printf("%s berhasil meminjam Buku %s!\n", person, book);
                }

            } else if (command == 5) {
                System.out.println("---------- Pengembalian Buku ----------");
                System.out.print("ID Anggota: ");
                String ID = input.nextLine();
                System.out.print("Judul Buku: ");
                String namaBuku = input.nextLine();
                System.out.print("Penulis Buku: ");
                String penerbit = input.nextLine();
                System.out.print("Tanggal pengembalian: ");
                String tanggal = input.nextLine();
                boolean adaID = false;
                boolean adaBuku = false;
                boolean lagiPinjam = false;
                Member person = null;
                Book book = null;
                if(members == null){        // Jika belum ada member terdaftar
                    adaID = false;
                }
                else{
                    for(Member member : members){
                        if(member.getID().equals(ID)){
                            adaID = true;       // Jika ID yang diinput sama dengan ID member
                            person = member;        
                            break;
                        }
                    }
                }
                if(books == null){          // Jika belum ada buku terdaftar
                    adaBuku = false;
                }
                else{
                    for(Book buku : books){
                        if(buku.getName().equals(namaBuku)){
                            adaBuku = true;
                            book = buku;
                            break;
                        }
                    }
                }
                if(person == null || person.getPinjam() == null){
                    lagiPinjam = false;        
                }
                else{
                    for(BookLoan bukuPinjam : person.getPinjam()){
                        if (bukuPinjam == null){    // Jika member belum meminjam buku
                            lagiPinjam = false;
                            break;
                        }
                        if(bukuPinjam.getBook().getName().equals(namaBuku)){      // Jika buku sedang dipinjam
                            lagiPinjam = bukuPinjam.getStatus();
                        }
                    }
                }
                if(!adaID){
                    System.out.printf("Anggota dengan ID %s tidak ditemukan\n", ID);
                }
                else if(!adaBuku){
                    System.out.printf("Buku %s oleh %s tidak ditemukan\n", namaBuku, penerbit);
                }
                else if(!lagiPinjam){
                    System.out.printf("Buku %s tidak sedang dipinjam\n", namaBuku);
                }
                else{
                    person.kembali(book, tanggal);      // Memanggil method untuk mengembalikan buku
                    System.out.printf("Buku %s berhasil dikembalikan oleh %s dengan denda Rp %d!\n",
                                    book, person, person.getFine());
                }

            } else if (command == 6) {
                System.out.println("---------- Pembayaran Denda ----------");
                System.out.print("ID Anggota: ");
                String ID = input.nextLine();
                System.out.print("Jumlah: ");
                long uang = input.nextLong();
                input.nextLine();
                boolean adaID = false;
                Member person = null;
                if(members == null){
                    adaID = false;
                }
                else{
                    for(Member member : members){           // Jika input ID sesuai dengan ID member
                        if(member.getID().equals(ID)){
                            adaID = true;
                            person = member;
                            break;
                        }
                    }
                }

                if(!adaID){
                    System.out.printf("Anggota dengan ID %s tidak ditemukan", ID);
                }
                else if(person.getFine() == 0){
                    System.out.printf("%s tidak memiliki denda", person);
                }
                else if(uang >= person.getFine()){
                    System.out.printf("%s berhasil membayar lunas denda", person);
                    person.bayarDenda(uang);
                    System.out.printf("Jumlah kembalian: Rp %d", uang-person.getFine());
                }
                else{
                    person.bayarDenda(uang);        // Memanggil method untuk bayar denda
                    System.out.printf("%s berhasil membayar denda sebesar Rp %d", person, uang);
                    System.out.printf("Sisa denda saat ini: Rp %d", person.getFine());
                }

            } else if (command == 7) {
                System.out.println("---------- Detail Anggota ----------");
                System.out.print("ID Anggota: ");
                String ID = input.nextLine();
                boolean adaID = false;
                Member person = null;
                if(members == null){
                    adaID = false;
                }
                else{
                    for(Member member : members){
                        if(member.getID().equals(ID)){      // Jika input ID sesuai dengan ID member
                            adaID = true;
                            person = member;
                            break;
                        }
                    }
                }
                if(!adaID){
                    System.out.printf("Anggota dengan ID %s tidak ditemukan", ID);
                }
                else{
                    person.detail();
                }

            } else if (command == 8) {
                if(members == null){
                    System.out.println("Belum ada anggota yang terdaftar pada sistem");
                }
                else{
                    System.out.println("---------- Peringkat Anggota ----------");
                    //Comparator<Member> byPoint = Comparator.comparing(Member::getPoint).reversed();
                    //Comparator<Member> byName = Comparator.comparing(Member::getName);
                    //Arrays.sort(members, byPoint.thenComparing(byName));
                    boolean cek = true;
                    while(cek){
                        cek = false;
                        for(int i=0;i<members.length-1;i++){
                            if(members[i].getPoint() < members[i+1].getPoint()){
                                Member temp = members[i];
                                members[i] = members[i+1];
                                members[i+1] = temp;
                                cek = true;
                            }
                            else if(members[i].getPoint() == members[i+1].getPoint()){
                                if(members[i].getName().charAt(0) > members[i+1].getName().charAt(0)){
                                    Member temp = members[i];
                                    members[i] = members[i+1];
                                    members[i+1] = temp;
                                    cek = true;
                                }
                            }
                        }
                    }

                    int counter = 1;
                    for(Member member : members){
                        System.out.printf("—------------- %d —-------------", counter);
                        System.out.printf("\nID Anggota: %s", member.getID());
                        System.out.printf("\nNama Anggota: %s", member);
                        System.out.printf("\nTotal Point: %d", member.getPoint());
                        System.out.printf("\nDenda: %d\n", member.getFine());
                        if(counter==3){
                            break;
                        }
                        counter++;
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

        input.close();
    }
}
