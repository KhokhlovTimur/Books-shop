package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import services.users.UsersService;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balance = req.getParameter("setBalance");
        HttpSession session = req.getSession();
        if(balance != null){
            User user = ((User)session.getAttribute("user"));
            int newBalance = Integer.parseInt(balance) + user.getBalance();
            user.setBalance(newBalance);
            ((UsersService) getServletContext().getAttribute("usersService"))
                    .updateUser(user);
        }
        resp.sendRedirect("/profile");
    }
}
