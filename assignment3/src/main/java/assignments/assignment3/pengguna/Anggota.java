package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Peminjaman;

import java.util.ArrayList;
import java.util.Comparator;
import assignments.assignment3.buku.Buku;

public abstract class Anggota extends Pengguna implements Comparable<Anggota>, CanBorrow {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    private long denda = 0;
    private int poin = 0;
    private int pinjamAktif = 0;
    private ArrayList<Peminjaman> daftarPeminjaman = new ArrayList<Peminjaman>();

    protected Anggota(){
    }

    protected Anggota(String nama){
        super(nama);
    }

    public String bayarDenda(long jumlahBayar) {     
        if(this.denda == 0){
            return String.format("%s tidak memiliki denda", getName());
        }    
        else{
            if (jumlahBayar >= this.denda){       // Jika uang yang dibayar lebih banyak dengan denda
                Long sblmBayar = denda;
                this.denda -= this.denda;         // Maka denda akan menjadi 0
                setFine(denda);
                return String.format("%s berhasil membayar lunas denda\nJumlah kembalian: Rp%s",
                getName(), jumlahBayar - sblmBayar);
            }
            else{
                this.denda -= jumlahBayar;      // Jika uang kurang, maka denda akan dikurangi uang yang diberikan
                return String.format("%s berhasil membayar denda sebesar Rp%d\nSisa denda saat ini: Rp%d",
                getName(), jumlahBayar, this.denda);
            }
        }
    }

    public String kembali(Buku buku, String tanggalPengembalian){
        boolean sedangPinjam = false;
        Peminjaman bukuPinjam = null;
        for(Peminjaman iniBukuPinjam : getDaftarPeminjaman()){
            if(iniBukuPinjam.getBook() == buku){
                sedangPinjam = true;
                bukuPinjam = iniBukuPinjam;
            }
        }
        if(!sedangPinjam){
            return String.format("Buku %s tidak sedang dipinjam oleh %s", buku.getName(), getName());
        }
        else{
            bukuPinjam.kembalikanBuku(tanggalPengembalian);
            bukuPinjam.setFine(bukuPinjam.hitungDenda());
            setJumlahPinjam(-1);
            bukuPinjam.getBook().setStok(bukuPinjam.getBook().getStok()+1);
            return String.format("Buku %s berhasil dikembalikan oleh %s dengan denda Rp%d!", 
                bukuPinjam.getBook().getName(), getName(), bukuPinjam.getFine());
        }
    }

    public void detail() {
        System.out.print(this);
        System.out.print("\nRiwayat Peminjaman Buku :");
        int counter = 1;
        if(daftarPeminjaman.size() == 0){
            System.out.print("\nBelum pernah meminjam buku");
        }
        else{
            for(Peminjaman bukuPinjam : daftarPeminjaman){
                System.out.printf("\n—------------- %d —-------------\n", counter);
                System.out.print(bukuPinjam.getBook());
                System.out.printf("\nTanggal Peminjaman: %s", bukuPinjam.getTanggalPinjam());
                System.out.printf("\nTanggal Pengembalian: %s", bukuPinjam.getTanggalKembali());
                System.out.printf("\nDenda: Rp%d", bukuPinjam.getFine());
                counter++;
            }
        }
    }

    public int getPoint(){
        return this.poin;
    }
    public void setPoint(int poin){
        this.poin += poin;
    }
    public long getFine(){
        return this.denda;
    }
    public void setFine(long denda){
        this.denda += denda;
    }
    public int getJumlahPinjam(){
        return this.pinjamAktif;
    }
    public void setJumlahPinjam(int num){
        this.pinjamAktif += num;
    }
    public ArrayList<Peminjaman> getDaftarPeminjaman(){
        return daftarPeminjaman;
    }
    public void setDaftarPeminjaman(ArrayList<Peminjaman> daftarPeminjaman) {
        this.daftarPeminjaman = daftarPeminjaman;
    }

    @Override
    public int compareTo(Anggota other){
        for(int i = 0;i < this.getName().length();i++){
            try{
                if(this.getName().charAt(i) == other.getName().charAt(i)){
                    continue;
                }
                else if(this.getName().charAt(i) > other.getName().charAt(i)){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            catch(IndexOutOfBoundsException e){
                return -1;
            }
        }
        return 0;
    }

    public static Comparator<Anggota> banding = new Comparator<Anggota>() {
        public int compare(Anggota siA, Anggota siB){
            return siA.compareTo(siB);
        }
    };  // Implementasi method berikut berdasarkan ->
        // https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/

    @Override
    public String toString(){
        return String.format("ID Anggota: %s\nNama Anggota: %s\nTotal Poin: %d\nDenda: Rp%d",
                                getId(), getName(), getPoint(), getFine());
    }
}
