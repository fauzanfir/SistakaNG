package assignments.assignment3.buku;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.util.concurrent.TimeUnit;
import assignments.assignment3.pengguna.Anggota;

public class Peminjaman {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    private static long DENDA_PER_HARI = 3000;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPeminjaman;
    private String tanggalPengembalian = "-";
    private long denda;
    private boolean status;

    public Peminjaman(){
    }
    // Men-construct object BookLoan
    public Peminjaman(Anggota anggota, Buku buku, String tanggalPeminjaman){
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPeminjaman = tanggalPeminjaman;
    }
    // Membuat method setter dan getter yang dibutuhkan
    public void kembalikanBuku(String tanggalPengembalian){
        this.tanggalPengembalian = tanggalPengembalian;
    }
    public long hitungDenda(){
        anggota.setPoint(anggota.getPoint()+buku.getCategory().getPoint());         // Point setiap member akan bertambah sesuai dengan
        buku.setStok(buku.getStok()+1);       // Menambahkan kembali stock buku
        this.status = false;                    // Menjadikan status tersebut false kembali
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");      // Untuk mengubah string ke dalam format tanggal
        try{
            Date TanggalKembali= sdf.parse(this.tanggalPengembalian);                     // Menjadikan String tanggal menjadi tanggal
            Date TanggalPinjam = sdf.parse(this.tanggalPeminjaman);
            long difference = TanggalKembali.getTime() - TanggalPinjam.getTime();       // Selang waktu kedua tanggal

            TimeUnit time = TimeUnit.DAYS;              // Untuk format hari
            long jumlahHari = time.convert(difference, TimeUnit.MILLISECONDS);  // Mengubah selang waktu menjadi hari
            if(jumlahHari > 7){                     // Jika pengembalian telat
                this.denda += (jumlahHari - 7) * DENDA_PER_HARI;     // Menambahkan denda
            }
            else{
                this.denda = 0;
            }
        }                                                   // point buku tersebut
        catch (ParseException exception){
            exception.printStackTrace();            // Untuk menghandle
        }
        anggota.setFine(anggota.getFine() + denda);
        return this.denda;
        // Ide mengurangi tanggal ini berasal dari https://www.delftstack.com/howto/java/java-subtract-dates/
    }
    public void setFine(long fine){
        this.denda = fine;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public String getTanggalPinjam(){
        return this.tanggalPeminjaman;
    }
    public Buku getBook(){
        return this.buku;
    }
    public Anggota getMember(){
        return anggota;
    }
    public long getDendaHari(){
        return DENDA_PER_HARI;
    }
    public String getTanggalKembali(){
        return tanggalPengembalian;
    }
    public boolean getStatus(){
        return this.status;
    }
    public long getFine(){
        return this.denda;
    }

    @Override
    public String toString(){
        return String.format("%s\nTanggal Peminjaman: %s\nTanggal Pengembalian: %s\nDenda: Rp%d", 
                                this.buku, this.tanggalPeminjaman, this.tanggalPengembalian, this.denda);
    }
}
