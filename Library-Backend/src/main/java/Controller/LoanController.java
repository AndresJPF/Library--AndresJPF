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
import java.util.List;

@WebServlet("/loans/*")
public class LoanController extends HttpServlet {

    private LoanDAO loanDAO;
    private Gson gson;

    @Override
    public void init() {
        loanDAO = new LoanDAO();
        gson = new Gson();
    }

    // GET 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");

            String pathInfo = req.getPathInfo();

            if (pathInfo == null || pathInfo.equals("/")) {
                // GET ALL
                List<Loan> loans = loanDAO.getLoan();
                res.getWriter().write(gson.toJson(loans));
            } else {
                // GET BY ID
                try {
                    int id = Integer.parseInt(pathInfo.substring(1));
                    Loan loan = loanDAO.getLoanById(id);
                    if (loan != null) {
                        res.getWriter().write(gson.toJson(loan));
                    } else {
                        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        res.getWriter().write("{\"error\":\"Loan not found\"}");
                    }
                } catch (NumberFormatException e) {
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    res.getWriter().write("{\"error\":\"Invalid ID format\"}");
                }
            }
    }

    // POST
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

        res.setContentType("application/json");
        res.getWriter().write("{\"message\":\"Loan created\"}");
    }
}