package assignments.assignment2;

// TODO
public class Member {
    private String id;
    private String name;
    private String dateOfBirth;
    private String studyProgram;
    private String angkatan;
    private long fine;
    private int point;
    private BookLoan[] bookLoans;

    public void pinjam(Book book, String loanDate) {
        // TODO: method untuk melakukan peminjaman oleh member
    }

    public void kembali(Book book, String returnDate) {
        // TODO: method untuk melakukan pengembalian oleh member
    }

    public void detail() {
        // TODO: method untuk menampilkan detail anggota
    }

    public void bayarDenda(long amount) {
        // TODO: method untuk melakukan pembayaran denda
    }

    @Override
    public String toString() {
        // TODO
        return "";
    }
}
