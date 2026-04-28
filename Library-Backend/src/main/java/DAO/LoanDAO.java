package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.ConnectionDB;
import Model.Loan;

public class LoanDAO {

    // CREATE
    public void addLoan(Loan loan) {
        try (Connection conn = ConnectionDB.connect()) {
            String sql = "INSERT INTO loans(loan_date, return_date, id_user, id_book) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(loan.getLoanDate().getTime()));
            ps.setDate(2, new java.sql.Date(loan.getReturnDate().getTime()));
            ps.setInt(3, loan.getUser());
            ps.setInt(4, loan.getBook());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en addLoan: " + e.getMessage());
        }
    }

    // READ
    public List<Loan> getLoan() {
        List<Loan> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.connect()) {
            String sql = "SELECT * FROM loans";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Loan l = new Loan();
                l.setIdLoan(rs.getInt("id_loan"));
                l.setLoanDate(rs.getDate("loan_date"));
                l.setReturnDate(rs.getDate("return_date"));
                l.setUser(rs.getInt("id_user"));
                l.setBook(rs.getInt("id_book"));
                list.add(l);
            }
        } catch (SQLException e) {
            System.out.println("Error en getLoan: " + e.getMessage());
        }
        return list;
    }
    
    public Loan getLoanById(int id) {
    Loan l = null;
    try (Connection conn = ConnectionDB.connect()) {
        String sql = "SELECT * FROM loans WHERE id_loan = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            l = new Loan();
            l.setIdLoan(rs.getInt("id_loan"));
            l.setLoanDate(rs.getDate("loan_date"));
            l.setReturnDate(rs.getDate("return_date"));
            l.setUser(rs.getInt("id_user"));
            l.setBook(rs.getInt("id_book"));
        }
    } catch (SQLException e) {
        System.out.println("Error en getLoanById: " + e.getMessage());
        
        }
        return l;
    }
}