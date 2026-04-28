package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.ConnectionDB;
import Model.Book;
import Model.Loan;

public class BookDAO {

    // CREATE
    public void addBook(Book book) {
        try (Connection conn = ConnectionDB.connect()) {
            String sql = "INSERT INTO books(title, isbn, year, id_author) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3, book.getYear());
            ps.setInt(4, book.getAuthor());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en addBook: " + e.getMessage());
        }
    }

    // READ
    public List<Book> getBooks() {
        List<Book> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.connect()) {
            String sql = "SELECT * FROM books WHERE b_status = 'A' OR b_status = 'L'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Book b = new Book();
                b.setIdBook(rs.getInt("id_book"));
                b.setTitle(rs.getString("title"));
                b.setIsbn(rs.getString("isbn"));
                b.setYear(rs.getString("year"));
                b.setAuthor(rs.getInt("id_author"));
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("Error en getBooks: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    public Book getBookById(int id) {
    Book b = null;
    try (Connection conn = ConnectionDB.connect()) {
        String sql = "SELECT * FROM books WHERE id_book = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            b = new Book();
                b.setIdBook(rs.getInt("id_book"));
                b.setTitle(rs.getString("title"));
                b.setIsbn(rs.getString("isbn"));
                b.setYear(rs.getString("year"));
                b.setAuthor(rs.getInt("id_author"));
        }
    } catch (SQLException e) {
        System.out.println("Error en getLoanById: " + e.getMessage());
    }
    return b;
}

    // UPDATE
    public void updateBook(Book book) {
        try (Connection conn = ConnectionDB.connect()) {
            String sql = "UPDATE books SET title=?, isbn=?, year=?, id_author=? WHERE id_book=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3, book.getYear());
            ps.setInt(4, book.getAuthor());
            ps.setInt(5, book.getIdBook());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en updateBook: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteBook(int id) {
        try (Connection conn = ConnectionDB.connect()) {
            String sql = "UPDATE books SET b_status='U' WHERE id_book=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en deleteBook: " + e.getMessage());
        }
    }
}