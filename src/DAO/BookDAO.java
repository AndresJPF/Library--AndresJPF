package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.ConnectionDB;
import Model.Book;


public class BookDAO {
    //CREATE

    public void addBook(Book book) {

        try(Connection conn = ConnectionDB.connect()){
            String sql = "INSERT INTO books(name, isbn, year, author) VALUES(?,?,?,?) ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getIsbn());
            ps.setDate(3, book.getYear());
            ps.setString(4, book.getAuthor());

            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    //READ
    public List<Book> getBooks(){
        List<Book> list = new ArrayList<>();

        try (Connection conn = ConnectionDB.connect()){
            String sql = "SELECT * FROM books";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Book b = new Book();

                b.setIdBook(rs.getInt("idBook"));
                b.setName(rs.getString("name"));
                b.setIsbn(rs.getString("isbn"));
                b.setYear(rs.getDate("year"));

                list.add(b);
            }

        }catch (Exception e) {
            System.out.println(e);
        }
        
    return list;

    }

    public void updateBook(Book book) {
        try(Connection conn = ConnectionDB.connect()){
            String sql = "UPDATE books SET name=?, isbn=?, year=? WHERE idBook=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getIsbn());
            ps.setDate(3, book.getYear());
            ps.setString(4, book.getAuthor());
            ps.setInt(5, book.getIdBook());

            ps.executeUpdate(sql);

        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteBook(int id) {
        try(Connection conn = ConnectionDB.connect()){
            String sql = "UPDATE books SET b_status='U' WHERE idBook=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate(sql);

        }catch (Exception e) {
            System.out.println(e);
        }
    }

}
