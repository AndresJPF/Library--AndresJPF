package Model;
import java.util.Date;

public class Book {

    private  int idBook;
    private  String name;
    private String isbn;
    private Date year;
    private Author author;

    public Book(){}

    public Book(int idBook, String name, String isbn, Date year, Author author){
        this.idBook = idBook;
        this.name = name;
        this.isbn = isbn;
        this.year = year;
        this.author = author;


    }
    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
