package controllers.servlets;

import dao.users.impl.UsersRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.users.UsersService;
import services.users.UsersServiceImpl;

import java.io.IOException;

@WebServlet("/admin")
public class AdminPage extends HttpServlet {
    private UsersService usersService;
    @Override
    public void init() throws ServletException {
        usersService = new UsersServiceImpl(new UsersRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("usersService", usersService);
        req.setAttribute("usersAll", usersService.findAllUsers());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
    }
}
