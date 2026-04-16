package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.ConnectionDB;
import Model.Author;
import Model.User;


public class AuthorDAO {

    //CREATE
    public void addAuthor(Author author){
        try (Connection conn = ConnectionDB.connect()){
            String sql = "INSERT INTO authors(name, lastName, nationality) values(?,?,?)";
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    //READ
    public List<Author> getAuthors(){
        List<Author> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.connect()){
            String sql = "SELECT * FROM authors";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Author a = new Author();
                a.setIdAuthor(rs.getInt("id_author"));
                a.setName(rs.getString("name"));
                a.setLastName(rs.getString("lastname"));
                a.setNationality(rs.getString("nationality"));

                list.add(a);
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return list;
    }

    //UPDATE
    public void updateAuthor(Author author){
        try(Connection conn = ConnectionDB.connect()){
            String sql = "UPDATE authors SET name=?, lastname=?, nationality=? WHERE id_authors=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, author.getName());
            ps.setString(2, author.getLastName());
            ps.setString(3, author.getNationality());

        } catch (SQLException e) {
            System.out.println(e);;
        }
    }

    //DELETE

    public void deletAuthor(int id){
        try(Connection conn = ConnectionDB.connect()){

            String sql = "UPDATE authors SET a_status=False id_author";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
