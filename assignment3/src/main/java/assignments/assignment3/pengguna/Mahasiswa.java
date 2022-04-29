package assignments.assignment3.pengguna;
import java.util.ArrayList;

import assignments.assignment3.IdGenerator;
import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

// Mahasiswa merupakan subclass dari Anggota
public class Mahasiswa extends Anggota{
    // Membuat attribute pada Mahasiswa dengan modifier sesuai UML
    public static int BATAS_JUMLAH_PEMINJAMAN_BUKU = 3;
    public static long BATAS_MAKSIMAL_DENDA = 5000;
    private String tanggalLahir;
    private String programStudi;
    private String angkatan;

    // Membuat constructor untuk Mahasiswa
    public Mahasiswa(){
    }

    public Mahasiswa(String nama, String tgl, String progStud, String akt){
        super(nama);
        this.tanggalLahir = tgl;
        this.programStudi = progStud;
        this.angkatan = akt;
        this.setId(generateId());
    }

    // Membuat ID mahasiswa sebagaimana TP1
    protected String generateId(){
        String ID = IdGenerator.generateId(programStudi, angkatan, tanggalLahir); 

        return ID;
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
            return "Denda lebih dari Rp" + BATAS_MAKSIMAL_DENDA;
        }
        else if(sedangPinjam){
            return String.format("Buku %s oleh %s sedang dipinjam", buku.getName(), buku.getAuthor());
        }
        else{
            Peminjaman bukuPinjam = new Peminjaman(this, buku, tanggalPeminjaman);      // Membuat objek Peminjaman
            ArrayList<Peminjaman> tempDaftar = getDaftarPeminjaman();
            tempDaftar.add(bukuPinjam);
            setDaftarPeminjaman(tempDaftar);                        // Mengupdate isi dari arrayList daftar buku dipinjam
            bukuPinjam.getBook().setStok(bukuPinjam.getBook().getStok()-1);
            ArrayList<CanBorrow> peminjam = buku.getDaftarPinjam();
            peminjam.add(this);
            buku.setDaftarPinjam(peminjam);                      // Mengupdate Anggota yang sudah meminjam buku tersebut
            setJumlahPinjam(1);
            return String.format("%s berhasil meminjam Buku %s!", getName(), buku.getName());
        }
    }
}
