package assignments.assignment3.pengguna;
import assignments.assignment3.buku.Buku;

// Canborrow merupakan interface yang akan berhubungan dengan buku dan anggota
public interface CanBorrow {
    // Membuat method abstract untuk Anggota
    public abstract String pinjam(Buku buku, String tanggalPeminjaman);
    public abstract String kembali(Buku buku, String tanggalPengembalian);
}
