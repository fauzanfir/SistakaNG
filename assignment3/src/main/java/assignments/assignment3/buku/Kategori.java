package assignments.assignment3.buku;

public class Kategori{
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    private String name;
    private int point;

    public Kategori(){
    }

    public Kategori(String nama, int poin){
        this.name = nama;
        this.point = poin;
    }
    public int getPoint(){
        return this.point;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Kategori: %s\nPoin: %d", this.name, this.point);
    }
}
