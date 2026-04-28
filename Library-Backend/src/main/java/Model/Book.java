package Model;
import java.util.Date;

public class Book {
    private int idBook;
    private String title;
    private String isbn;
    private String year;
    private int author;

    public Book() {}

    public Book(int idBook, String title, String isbn, String year, int author) {
        this.idBook = idBook;
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.author = author;
    }

    public int getIdBook() { return idBook; }
    public void setIdBook(int idBook) { this.idBook = idBook; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public int getAuthor() { return author; }
    public void setAuthor(int author) { this.author = author; }
}