package Controller;

import DAO.UserDAO;
import Model.User;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServerlet("/users")
public class UserController extends HttpServlet{
    private UserDAO userDAO;
    private Gson gson;

    @Override
    public void init(){
        userDAO = new UserDAO();
        gson = new Gson();

    }

    //GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");

            List<User> users = userDAO.getUsers();

            String json = gson.toJson(users);
            res.getWriter().write(json);
    }

    //POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
                String name = req.getParameter("name");;
                String lastname = req.getParameter("lastname");
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");

                User user = new User();
                user.setName(name);
                user.setLastName(lastname);
                user.setEmail(email);
                user.setPhone(phone);

                userDAO.addUser(user);
                res.setContentType("application/json");
                res.getWriter().write("{\"message\":\"User created successfully\"}");

    }
}
