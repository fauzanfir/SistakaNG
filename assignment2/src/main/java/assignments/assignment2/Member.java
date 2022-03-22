package assignments.assignment2;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.util.concurrent.TimeUnit;


// TODO
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

    Member(String nama, String progStud, String angkatan, String tanggalLahir, String ID){
        this.name = nama;
        this.studyProgram = progStud;
        this.angkatan = angkatan;
        this.dateOfBirth = tanggalLahir;
        this.id = ID;
        this.point = 0;
        this.fine = 0;
    }
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

    public void pinjam(Book book, String loanDate) {
        BookLoan bukuPinjam = new BookLoan(Member.this, book, loanDate);
        if(this.bookLoans[0] == null){
            this.bookLoans[0] = bukuPinjam;
        }
        else{
            BookLoan[] temp = new BookLoan[bookLoans.length+1];
            for(int i=0;i<bookLoans.length;i++){
                if (bookLoans[i] == null){
                    this.bookLoans[0] = bukuPinjam;
                }
                else{
                    temp[i] = bookLoans[i];
                }
            }
            temp[bookLoans.length-1] = bukuPinjam;
            this.bookLoans = temp.clone();
            book.setStock(book.getStock()-1);
        }
        bukuPinjam.setStatus(true);
    }

    public void kembali(Book book, String returnDate){
        BookLoan buku = null;
        for(int i=0;i<bookLoans.length;i++){
            if (bookLoans[i].getBook() == book){
                buku = bookLoans[i];
            }
        }
        book.setStock(book.getStock()+1);
        buku.setStatus(false);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try{
            Date TanggalKembali= sdf.parse(returnDate);
            Date TanggalPinjam = sdf.parse(buku.getTanggalPinjam());
            long diff = TanggalKembali.getTime() - TanggalPinjam.getTime();

            TimeUnit time = TimeUnit.DAYS; 
            long jumlahHari = time.convert(diff, TimeUnit.MILLISECONDS);
            this.fine = (jumlahHari - 7) * buku.getDendaHari();
            buku.setFine(this.fine);
            point += book.getCategory().getPoint();
            }
            catch (ParseException exception){
                exception.printStackTrace();
            }
        // Ide mengurangi tanggal ini berasal dari https://www.delftstack.com/howto/java/java-subtract-dates/
    }

    public void detail() {
        System.out.printf("ID Anggota: %s", this.id);
        System.out.printf("\nNama Anggota: %s", this.name);
        System.out.printf("\nTotal point: %d", this.point);
        System.out.printf("\nDenda: %d", this.fine);
        System.out.println("\nRiwayat Peminjaman Buku :");
        int counter = 1;
        for(BookLoan bukuPinjam : bookLoans){
            System.out.printf("—------------- %d —-------------", counter);
            System.out.printf("\nJudul Buku: %s", bukuPinjam.getBook().getTitle());
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

    public void bayarDenda(long amount) {
        if (amount >= this.fine){
            this.fine -= this.fine;
        }
        else{
            this.fine -= amount;
        }
    }

    @Override
    public String toString() {
        // TODO
        return "";
    }
}
