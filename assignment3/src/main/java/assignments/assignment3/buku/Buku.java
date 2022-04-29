package assignments.assignment3.buku;

import java.util.ArrayList;

import assignments.assignment3.pengguna.CanBorrow;

public class Buku {
    private String judul;
    private String penulis;
    private String penerbit;
    private int stokAwal;
    private int stok;
    private Kategori kategori;
    private ArrayList<CanBorrow> daftarPeminjam = new ArrayList<CanBorrow>();

    public Buku(){
    }
    // Men-construct object Book
    public Buku(String judul, String pengarang, String penerbit, Kategori kategori, int stok){
        this.judul = judul;
        this.penulis = pengarang;
        this.penerbit = penerbit;
        this.kategori = kategori;
        this.stokAwal = stok;
        this.stok = stok;
    }
    // Membuat method setter dan getter sebutuhnya
    public String getName(){
        return this.judul;
    }
    public void setStok(int stock){
        this.stok = stock;
    }
    public String getAuthor(){
        return this.penulis;
    }
    public int getStok(){
        return this.stok;
    }
    public Kategori getCategory(){
        return this.kategori;
    }
    public String getPublisher(){
        return this.penerbit;
    }
    public ArrayList<CanBorrow> getDaftarPinjam(){
        return daftarPeminjam;
    }
    public void setDaftarPinjam(ArrayList<CanBorrow> daftarPeminjam){
        this.daftarPeminjam = daftarPeminjam;
    }
    public int getStokAwal(){
        return this.stokAwal;
    }

    @Override
    public String toString() {
        return String.format("Judul Buku: %s\nPenulis Buku: %s\nPenerbit buku: %s\n%s", 
                                this.judul, this.penulis, this.penerbit, this.kategori);
    }
}
