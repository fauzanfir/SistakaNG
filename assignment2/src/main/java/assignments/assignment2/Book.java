package assignments.assignment2;

// TODO
public class Book {
    private String title;
    private String author;
    private String publisher;
    private int stok;
    private Category category;

    Book(){
    }

    public Book(String judul, String pengarang, String penerbit, Category kategori, int stok){
        this.title = judul;
        this.author = pengarang;
        this.publisher = penerbit;
        this.category = kategori;
        this.stok = stok;
    }

    public void setStock(int stock){
        this.stok = stock;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }
    public int getStock(){
        return this.stok;
    }
    public Category getCategory(){
        return this.category;
    }
    public String getPublisher(){
        return this.publisher;
    }

    @Override
    public String toString() {
        // TODO
        return "";
    }
}
