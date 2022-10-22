package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cart;
import models.User;
import services.carts.CartServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] deleteBooks = req.getParameterValues("deleteFromCart");
        CartServiceImpl cartService = ((CartServiceImpl) getServletContext().getAttribute("cartService"));
        if (deleteBooks != null) {
            for (String id : deleteBooks) {
                cartService.deleteCartByBookIdUserId(Long.valueOf(id), ((User) req.getSession().getAttribute("user")).getId());
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(req, resp);
    }
}
