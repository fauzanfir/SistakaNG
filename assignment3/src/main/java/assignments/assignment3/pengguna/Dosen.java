package assignments.assignment3.pengguna;
import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;
import java.util.ArrayList;

// Dosen merupakan subclass dari Anggota
public class Dosen extends Anggota{
    // Membuat attribute pada Dosen dengan modifier sesuai UML
    private static int jumlahDosen = 0;
    public static int BATAS_JUMLAH_PEMINJAMAN_BUKU = 5;
    public static long BATAS_MAKSIMAL_DENDA = 10000;

    // Membuat consturctor untuk Dosen
    public Dosen(){
    }

    public Dosen(String nama){
        super(nama);
        setId(generateId());
    }

    // Membuat method untuk generate id
    protected String generateId(){
        jumlahDosen += 1;
        return String.format("DOSEN-%d", jumlahDosen); 
    }

    public String pinjam(Buku buku, String tanggalPeminjaman){
        boolean sedangPinjam = false;

        for(Peminjaman iniBukupinjam : getDaftarPeminjaman()){
            if(iniBukupinjam.getBook() == buku){
                if(iniBukupinjam.getTanggalKembali().equals("-")){
                    sedangPinjam = true;            // Jika sudah ada buku yang sedang dipinjam
                }
            }
        }
        if(getJumlahPinjam() == BATAS_JUMLAH_PEMINJAMAN_BUKU){
            return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
        }
        else if(getFine() >= BATAS_MAKSIMAL_DENDA){
            return "Denda lebih dari Rp5000";
        }
        else if(sedangPinjam){
            return String.format("Buku %s oleh %s sedang dipinjam", buku.getName(), buku.getAuthor());
        }
        else{
            Peminjaman bukuPinjam = new Peminjaman(this, buku, tanggalPeminjaman);  // Membuat objek Peminjaman
            ArrayList<Peminjaman> tempDaftar = getDaftarPeminjaman();
            tempDaftar.add(bukuPinjam); 
            setDaftarPeminjaman(tempDaftar);                // Mengupdate isi dari arrayList daftar buku dipinjam
            bukuPinjam.getBook().setStok(bukuPinjam.getBook().getStok()-1);
            ArrayList<CanBorrow> peminjam = buku.getDaftarPinjam();
            peminjam.add(this);
            buku.setDaftarPinjam(peminjam);             // Mengupdate Anggota yang sudah meminjam buku tersebut
            setJumlahPinjam(1);
            return String.format("%s berhasil meminjam Buku %s!", getName(), buku.getName());
        }
    }
}
