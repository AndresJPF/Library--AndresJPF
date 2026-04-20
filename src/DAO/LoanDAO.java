package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.ConnectionDB;
import Model.Author;
import Model.Loan;



public class LoanDAO {

    //CREAT
    public void addLoan(Loan loan){
        try(Connection conn = ConnectionDB.connect()){
            String sql = "INSERT INTO loans(loan_date, return_date, id_user, id_book) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, new Date(loan.getLoanDate().getTime()));
            ps.setDate(2, new Date(loan.getReturnDate().getTime()));
            ps.setInt(3, loan.getUser());
            ps.setInt(4, loan.getBook());

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //READ
    public List<Loan> getLoan(){
        List<Loan> list = new ArrayList<>();

        try(Connection connection = ConnectionDB.connect()){

            String sql = "SELECT * FROM * loans";
            Statement st =  connection.createStatement();
            ResultSet rs =  st.executeQuery(sql);

            while (rs.next()){
                Loan l = new Loan();
                l.setLoanDate(rs.getDate("loanDate"));
                l.setReturnDate(rs.getDate("loanReturnDate"));
                l.setUser(rs.getInt("id_user"));
                l.setBook(rs.getInt("id_book"));

                list.add(l);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    //UPDATE
    public void updateLoan(Loan loan){
        //Estos valores no se deben modificar
    }

    //DELETE
    public void deleteLoan(Loan loan){
        //Estos valores no se deben borrar
    }
}
