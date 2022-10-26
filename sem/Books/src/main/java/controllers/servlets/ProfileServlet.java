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
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        User user = ((User) session.getAttribute("user"));
        UsersService usersService = ((UsersService) getServletContext().getAttribute("usersService"));
        if (balance != null && balance.replaceAll("\\s+", "").length() < 10) {
            int newBalance = Integer.parseInt(balance) + user.getBalance();
            user.setBalance(newBalance);
            usersService.updateUser(user);
        }

        if (email != null) {
            user.setEmail(email);
            usersService.updateUser(user);
        }
        resp.sendRedirect("/profile");
    }
}
