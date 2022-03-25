package assignments.assignment2;

// Membuat atribut dalam class BookLoan
public class BookLoan {
    private static long DENDA_PER_HARI;
    private Member member;
    private Book book;
    private String loanDate;
    private String returnDate;
    private long fine;
    private boolean status;

    BookLoan(){
    }
    // Men-construct object BookLoan
    BookLoan(Member member, Book book, String loanDate){
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
        this.DENDA_PER_HARI = 3000;
    }
    // Membuat method setter dan getter yang dibutuhkan
    public void setTanggalKembali(String tanggal){
        this.returnDate = tanggal;
    }
    public void setFine(long fine){
        this.fine = fine;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public String getTanggalPinjam(){
        return this.loanDate;
    }
    public Book getBook(){
        return this.book;
    }
    public Member getMember(){
        return member;
    }
    public long getDendaHari(){
        return DENDA_PER_HARI;
    }
    public String getTanggalKembali(){
        if(returnDate == null){     // Jika memeber belum mengembalikan
            return "-";                 // Maka akan men-return string "-"
        }
        else{
            return returnDate;      // Jika member sudah mengembalikan maka akan di return
        }
    }
    public boolean getStatus(){
        return this.status;
    }
    public long getFine(){
        return this.fine;
    }
}
