package assignments.assignment3.pengguna;
import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

public class Dosen extends Anggota{
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    private static int jumlahDosen = 0;
    public static int BATAS_JUMLAH_PEMINJAMAN_BUKU = 5;
    public static long BATAS_MAKSIMAL_DENDA = 10000;

    public Dosen(){
    }

    public Dosen(String nama){
        super(nama);
        setId(generateId());
    }

    protected String generateId(){
        jumlahDosen += 1;
        return String.format("DOSEN-%d", jumlahDosen); 
    }

    public String pinjam(Buku buku, String tanggalPeminjaman){
        boolean sedangPinjam = false;

        for(Peminjaman iniBukupinjam : getDaftarPeminjaman()){
            if(iniBukupinjam.getBook() == buku){
                if(iniBukupinjam.getTanggalKembali().equals("-")){
                    sedangPinjam = true;
                }
            }
        }
        if(getDaftarPeminjaman().size() == BATAS_JUMLAH_PEMINJAMAN_BUKU){
            return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
        }
        else if(getFine() >= BATAS_MAKSIMAL_DENDA){
            return "Denda lebih dari Rp5000";
        }
        else if(sedangPinjam){
            return String.format("Buku %s oleh %s sedang dipinjam", buku.getName(), buku.getAuthor());
        }
        else{
            Peminjaman bukuPinjam = new Peminjaman(this, buku, tanggalPeminjaman);
            getDaftarPeminjaman().add(bukuPinjam);
            return String.format("%s berhasil meminjam Buku %s!", getName(), buku.getName());
        }
    }
}
