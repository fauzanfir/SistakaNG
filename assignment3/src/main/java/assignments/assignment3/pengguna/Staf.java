package assignments.assignment3.pengguna;

public class Staf extends Pengguna {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    private static int jumlahStaf = 0;
    
    public Staf(){
    }

    public Staf(String nama){
        super(nama);
        setId(generateId());
    }

    protected String generateId(){
        jumlahStaf += 1;
        return String.format("STAF-%d", jumlahStaf);
    }

    @Override
    public String toString(){
        return String.format("ID Staf: %s\nNama Staf: %s", getId(), getName());
    }

}
