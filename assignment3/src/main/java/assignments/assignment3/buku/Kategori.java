package assignments.assignment3.buku;

public class Kategori{
    // Membuat attribute pada Kategori dengan modifier sesuai UML
    private String name;
    private int point;

    // Membuat constructor untuk Kategori
    public Kategori(){
    }

    public Kategori(String nama, int poin){
        this.name = nama;
        this.point = poin;
    }

    // Membuat method setter getter secukupnya
    public int getPoint(){
        return this.point;
    }
    public String getName(){
        return this.name;
    }

    // Membuat method overrider toString
    @Override
    public String toString() {
        return String.format("Kategori: %s\nPoin: %d", this.name, this.point);
    }
}
