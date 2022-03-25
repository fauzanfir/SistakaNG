package assignments.assignment2;

// Membuat atribut pada class Buku
public class Book {
    private String title;
    private String author;
    private String publisher;
    private int stok;
    private Category category;

    Book(){
    }
    // Men-construct object Book
    public Book(String judul, String pengarang, String penerbit, Category kategori, int stok){
        this.title = judul;
        this.author = pengarang;
        this.publisher = penerbit;
        this.category = kategori;
        this.stok = stok;
    }
    // Membuat method setter dan getter sebutuhnya
    public String getName(){
        return this.title;
    }
    public void setStock(int stock){
        this.stok = stock;
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
        return this.title;
    }
}
