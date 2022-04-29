package assignments.assignment3.pengguna;

public abstract class Pengguna {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    private String id;
    private String nama;

    protected Pengguna(){
    }

    protected Pengguna(String nama){
        this.nama = nama;
    }

    protected abstract String generateId();
    public abstract String toString();

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
