package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.ConnectionDB;
import Model.User;

public class UserDAO{

    //  CREATE
    public void addUser(User user) {
        try (Connection conn = ConnectionDB.connect()) {

            String sql = "INSERT INTO users(name,email,phone) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //  READ (LISTAR)
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();

        try (Connection conn = ConnectionDB.connect()) {

            String sql = "SELECT * FROM users";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                User u = new User();
                u.setIdUser(rs.getInt("id_user"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));

                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    //  UPDATE
    public void updateUser(User user) {
        try (Connection conn = ConnectionDB.connect()) {

            String sql = "UPDATE users SET name=?, email=?, phone=? WHERE id_user=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setInt(4, user.getIdUser());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //  DELETE...aquí es un eliminado completo, pero se recomienda usar ESTADO, esto quiere decir que toca agregar el campo a nivelk de BD y ajustar el objeto, para jugar conb ese campo
    public void deleteUser(int id) {
        try (Connection conn = ConnectionDB.connect()) {

            String sql = "UPDATE users SET u_status=False WHERE id_user=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}