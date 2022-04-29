package assignments.assignment3.pengguna;
import assignments.assignment3.buku.Buku;

public interface CanBorrow {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    public abstract String pinjam(Buku buku, String tanggalPeminjaman);
    public abstract String kembali(Buku buku, String tanggalPengembalian);
}
