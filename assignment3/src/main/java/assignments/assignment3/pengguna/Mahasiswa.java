package assignments.assignment3.pengguna;
import java.util.ArrayList;

import assignments.assignment3.IdGenerator;
import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

public class Mahasiswa extends Anggota{
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    public static int BATAS_JUMLAH_PEMINJAMAN_BUKU = 3;
    public static long BATAS_MAKSIMAL_DENDA = 5000;
    private String tanggalLahir;
    private String programStudi;
    private String angkatan;

    public Mahasiswa(){
    }

    public Mahasiswa(String nama, String tgl, String progStud, String akt){
        super(nama);
        this.tanggalLahir = tgl;
        this.programStudi = progStud;
        this.angkatan = akt;
        this.setId(generateId());
    }

    protected String generateId(){
        String ID = IdGenerator.generateId(programStudi, angkatan, tanggalLahir); 

        return ID;
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
            Peminjaman bukuPinjam = new Peminjaman(this, buku, tanggalPeminjaman);
            ArrayList<Peminjaman> tempDaftar = getDaftarPeminjaman();
            tempDaftar.add(bukuPinjam);
            setDaftarPeminjaman(tempDaftar);
            bukuPinjam.getBook().setStok(bukuPinjam.getBook().getStok()-1);
            ArrayList<CanBorrow> peminjam = buku.getDaftarPinjam();
            peminjam.add(this);
            buku.setDaftarPinjam(peminjam);
            setJumlahPinjam(1);
            return String.format("%s berhasil meminjam Buku %s!", getName(), buku.getName());
        }
    }
}
