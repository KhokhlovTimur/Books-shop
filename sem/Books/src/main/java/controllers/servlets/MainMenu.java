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

@WebServlet("/menu")
public class MainMenu extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        usersService = new UsersServiceImpl(new UsersRepositoryImpl());
        getServletContext().setAttribute("usersService", usersService.findAllUsers());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(req, resp);
    }
}
