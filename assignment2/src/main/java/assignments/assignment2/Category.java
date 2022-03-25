package assignments.assignment2;

public class Category {
    private String name;
    private int point;

    Category(){
    }

    Category(String nama, int poin){
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
        return this.name;
    }
}
