package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.carts.CartService;

import java.io.IOException;

@WebServlet("/menu")
public class MainMenu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartBook = req.getParameter("toCart");
        User user = (User) req.getSession().getAttribute("user");
        CartService cartService = (CartService) getServletContext().getAttribute("cartService");
        if(cartBook != null && user != null){
            cartService.saveBookToCart(Long.valueOf(cartBook), user.getId());
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(req, resp);
    }
}
