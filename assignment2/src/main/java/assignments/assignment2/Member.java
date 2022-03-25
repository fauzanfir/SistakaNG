// Mengimport class pada library java
package assignments.assignment2;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.util.concurrent.TimeUnit;


// Membuat attribute untuk member
public class Member {
    private String id;
    private String name;
    private String dateOfBirth;
    private String studyProgram;
    private String angkatan;
    private long fine;
    private int point;
    private BookLoan[] bookLoans = new BookLoan[1];

    Member(){
    }

    // Men-construct object member
    Member(String nama, String progStud, String angkatan, String tanggalLahir, String ID){
        this.name = nama;
        this.studyProgram = progStud;
        this.angkatan = angkatan;
        this.dateOfBirth = tanggalLahir;
        this.id = ID;
        this.point = 0;
        this.fine = 0;
    }

    // Membuat method setter dan getter yang dibutuhkan
    public String getName(){
        return this.name;
    }
    public int getPoint(){
        return this.point;
    }
    public Long getFine(){
        return this.fine;
    }
    public String getID(){
        return this.id;
    }
    public BookLoan[] getPinjam(){
        return this.bookLoans;
    }

    // Membuat method pinjam untuk meminjam buku
    public void pinjam(Book book, String loanDate) {        // Menerima parameter Buku dan String
        BookLoan bukuPinjam = new BookLoan(Member.this, book, loanDate);        // Membuat object BookLoan
        if(this.bookLoans[0] == null){          // Handle jika belum ada buku yang dipinjam
            this.bookLoans[0] = bukuPinjam;
        }
        else{                       // Jika member sudah pernah pinjam buku
            // Memasukkan buku kedalam array
            BookLoan[] temp = new BookLoan[bookLoans.length+1];         
            for(int i=0;i<bookLoans.length;i++){
                if (bookLoans[i] == null){
                    this.bookLoans[0] = bukuPinjam;
                }
                else{
                    temp[i] = bookLoans[i];
                }
            }
            temp[bookLoans.length] = bukuPinjam;
            this.bookLoans = temp.clone();
            book.setStock(book.getStock()-1);       // Stock pada buku akan berkurang
        }
        bukuPinjam.setStatus(true);         // Status true agar tahu buku tersebut sedang dipinjam oleh member
    }

    // Membuat method untuk mengembalikan buku
    public void kembali(Book book, String returnDate){         // Menerima parameter Book dan String
        this.point += book.getCategory().getPoint();         // Point setiap member akan bertambah sesuai dengan
        BookLoan buku = null;       // Menginisiasi buku
        for(int i=0;i<bookLoans.length;i++){
            if (bookLoans[i].getBook() == book){
                buku = bookLoans[i];            // Mengambil object yang sesuai dengan buku yang ingin dikembalikan
            }
        }
        book.setStock(book.getStock()+1);       // Menambahkan kembali stock buku
        buku.setStatus(false);                      // Menjadikan status tersebut false kembali
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");      // Untuk mengubah string ke dalam format tanggal
        try{
            Date TanggalKembali= sdf.parse(returnDate);                     // Menjadikan String tanggal menjadi tanggal
            Date TanggalPinjam = sdf.parse(buku.getTanggalPinjam());
            long difference = TanggalKembali.getTime() - TanggalPinjam.getTime();       // Selang waktu kedua tanggal

            TimeUnit time = TimeUnit.DAYS;              // Untuk format hari
            long jumlahHari = time.convert(difference, TimeUnit.MILLISECONDS);  // Mengubah selang waktu menjadi hari
            if(jumlahHari > 7){                     // Jika pengembalian telat
                this.fine += (jumlahHari - 7) * buku.getDendaHari();     // Menambahkan denda
            }
            buku.setFine(this.fine);
        }                                                   // point buku tersebut
        catch (ParseException exception){
            exception.printStackTrace();            // Untuk menghandle
        }
        // Ide mengurangi tanggal ini berasal dari https://www.delftstack.com/howto/java/java-subtract-dates/
    }

    // Membuat method detail untuk mencetak detail dan riwayat member
    public void detail() {
        System.out.printf("ID Anggota: %s", this.id);
        System.out.printf("\nNama Anggota: %s", this.name);
        System.out.printf("\nTotal point: %d", this.point);
        System.out.printf("\nDenda: %d", this.fine);
        System.out.println("\nRiwayat Peminjaman Buku :");
        int counter = 1;
        boolean buku = true;
        for(BookLoan bukuPinjam : bookLoans){
            System.out.printf("—------------- %d —-------------", counter);
            System.out.printf("\nJudul Buku: %s", bukuPinjam.getBook());
            System.out.printf("\nPenulis Buku: %s", bukuPinjam.getBook().getAuthor());
            System.out.printf("\nPenerbit Buku: %s", bukuPinjam.getBook().getPublisher());
            System.out.printf("\nKategori: %s", bukuPinjam.getBook().getCategory().getName());
            System.out.printf("\nPoint: %d", bukuPinjam.getBook().getCategory().getPoint());
            System.out.printf("\nTanggal Peminjaman: %s", bukuPinjam.getTanggalPinjam());
            System.out.printf("\nTanggal Pengembalian: %s", bukuPinjam.getTanggalKembali());
            System.out.printf("\nDenda: Rp %d\n", bukuPinjam.getFine());
            counter++;
        }
    }
    // Membuat method untuk membayar denda
    public void bayarDenda(long amount) {         
        if (amount >= this.fine){       // Jika uang yang dibayar lebih banyak dengan denda
            this.fine -= this.fine;         // Maka denda akan menjadi 0
        }
        else{
            this.fine -= amount;      // Jika uang kurang, maka denda akan dikurangi uang yang diberikan
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
