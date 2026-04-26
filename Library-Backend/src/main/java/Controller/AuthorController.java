package Controller;

import DAO.AuthorDAO;
import Model.Author;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/authors")
public class AuthorController extends HttpServlet {

    private AuthorDAO authorDAO;
    private Gson gson;

    @Override
    public void init() {
        authorDAO = new AuthorDAO();
        gson = new Gson();
    }

    // GET 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        List<Author> authors = authorDAO.getAuthors();
        String json = gson.toJson(authors);

        res.getWriter().write(json);
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String nationality = req.getParameter("nationality");

        Author author = new Author();
        author.setName(name);
        author.setLastName(lastname);
        author.setNationality(nationality);

        authorDAO.addAuthor(author);

        res.setContentType("application/json");
        res.getWriter().write("{\"message\":\"Author created\"}");
    }

    // PUT
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String nationality = req.getParameter("nationality");

        Author author = new Author();
        author.setIdAuthor(id);
        author.setName(name);
        author.setLastName(lastname);
        author.setNationality(nationality);

        authorDAO.updateAuthor(author);

        res.getWriter().write("{\"message\":\"Author updated\"}");
    }

    // DELETE
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        authorDAO.deletAuthor(id);

        res.getWriter().write("{\"message\":\"Author deleted\"}");
    }
}