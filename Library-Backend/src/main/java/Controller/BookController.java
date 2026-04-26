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

@WebServlet("/books")
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
        List<Book> books = bookDAO.getBooks();
        res.getWriter().write(gson.toJson(books));
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String isbn = req.getParameter("isbn");
        int authorId = Integer.parseInt(req.getParameter("authorId"));

        Author author = new Author();
        author.setIdAuthor(authorId);

        Book book = new Book();
        book.setName(name);
        book.setIsbn(isbn);
        book.setAuthor(name);

        bookDAO.addBook(book);

        res.getWriter().write("{\"message\":\"Book created\"}");
    }

    // PUT
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String isbn = req.getParameter("isbn");

        Book book = new Book();
        book.setIdBook(id);
        book.setName(name);
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