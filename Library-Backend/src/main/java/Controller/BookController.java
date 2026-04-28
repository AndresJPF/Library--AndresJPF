package Controller;

import DAO.BookDAO;
import Model.Book;
import Model.Author;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/books/*")
public class BookController extends HttpServlet {

    private BookDAO bookDAO;
    private Gson gson;

    @Override
    public void init() {
        bookDAO = new BookDAO();
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
                    //GET ALL
                    List<Book> books = bookDAO.getBooks();
                    String json = gson.toJson(books);
                    res.getWriter().write(json);
                }else{
                    // Get By Id
                    try{
                        int id = Integer.parseInt(pathInfo.substring(1));
                        Book book = bookDAO.getBookById(id);
                        if (book != null){
                            res.getWriter().write(gson.toJson(book));
                        }else {
                            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                            res.getWriter().write("{\"error\":\"Loan not found\"}");
                        }
                    }catch (NumberFormatException e) {
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        res.getWriter().write("{\"error\":\"Invalid ID format\"}");
                    }
                }
            }           

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

                String title = req.getParameter("title");
                String isbn = req.getParameter("isbn");
                int id_author = Integer.parseInt(req.getParameter("id_author"));

                Book book = new Book();
                book.setTitle(title);
                book.setIsbn(isbn);
                book.setAuthor(id_author);

                bookDAO.addBook(book);

                res.setContentType("application/json");
                res.getWriter().write("{\"message\":\"Book created\"}");
            }

    // PUT
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");

        Book book = new Book();
        book.setIdBook(id);
        book.setTitle(title);
        book.setIsbn(isbn);

        bookDAO.updateBook(book);

        res.getWriter().write("{\"message\":\"Book updated\"}");
    }

    // DELETE
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        bookDAO.deleteBook(id);

        res.getWriter().write("{\"message\":\"Book deleted\"}");
    }
}