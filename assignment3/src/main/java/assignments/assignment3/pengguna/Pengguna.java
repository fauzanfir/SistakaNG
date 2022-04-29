package assignments.assignment3.pengguna;

public abstract class Pengguna {
    // Membuat attribute pada Pengguna dengan modifier sesuai UML
    private String id;
    private String nama;

    // Membuat constructor tertutup untuk Pengguna
    protected Pengguna(){
    }

    protected Pengguna(String nama){
        this.nama = nama;
    }

    // Membuat method abstract unutuk diimplementasikan di subclass nya
    protected abstract String generateId();
    public abstract String toString();

    // Membuat method setter getter secukupnya
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.nama;
    }
}
