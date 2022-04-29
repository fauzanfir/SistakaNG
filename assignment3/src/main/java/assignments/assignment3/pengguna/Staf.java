package assignments.assignment3.pengguna;

public class Staf extends Pengguna {
    // Membuat attribute pada Staf dengan modifier sesuai UML
    private static int jumlahStaf = 0;
    
    // Membuat constructor untuk Staf
    public Staf(){
    }

    public Staf(String nama){
        super(nama);
        setId(generateId());
    }

    // Membuat method untuk generate ID
    protected String generateId(){
        jumlahStaf += 1;
        return String.format("STAF-%d", jumlahStaf);
    }

    // Membuat method overrider toString
    @Override
    public String toString(){
        return String.format("ID Staf: %s\nNama Staf: %s", getId(), getName());
    }

}
