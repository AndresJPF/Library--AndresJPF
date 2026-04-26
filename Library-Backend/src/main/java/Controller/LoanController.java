package Controller;

import DAO.LoanDAO;
import Model.Loan;
import Model.User;
import Model.Book;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/loans")
public class LoanController extends HttpServlet {

    private LoanDAO loanDAO;
    private Gson gson;

    @Override
    public void init() {
        loanDAO = new LoanDAO();
        gson = new Gson();
    }

    // POST - Crear préstamo
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int userId = Integer.parseInt(req.getParameter("userId"));
        int bookId = Integer.parseInt(req.getParameter("bookId"));

        User user = new User();
        user.setIdUser(userId);

        Book book = new Book();
        book.setIdBook(bookId);

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(new Date());
        loan.setReturnDate(new Date());

        loanDAO.addLoan(loan);

        res.getWriter().write("{\"message\":\"Loan created\"}");
    }
}