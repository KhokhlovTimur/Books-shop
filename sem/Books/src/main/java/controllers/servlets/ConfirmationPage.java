package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cart;
import models.Order;
import models.User;
import services.carts.CartServiceImpl;
import services.orderBookService.OrderBookServiceImpl;
import services.orderService.OrderService;
import services.utils.CartSumService;
import services.utils.MapService;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart/confirmation")
public class ConfirmationPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/confirmation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("confirmLogin");
        Long userId = ((User) req.getSession().getAttribute("user")).getId();
        OrderBookServiceImpl orderBookService = ((OrderBookServiceImpl) getServletContext().getAttribute("orderBookService"));
        CartServiceImpl cartService = (CartServiceImpl) getServletContext().getAttribute("cartService");
        if (login != null && login.equals(req.getSession().getAttribute("login"))) {
            List<Cart> carts = ((CartServiceImpl) getServletContext().getAttribute("cartService")).findAllBooks(userId);
            Order newOrder = Order.builder()
                    .userId(userId)
                    .price((long) ((CartSumService) getServletContext().getAttribute("cartSumService")).getCartSumByUserId(userId))
                    .build();
            ((OrderService) getServletContext().getAttribute("orderService")).saveOrder(newOrder);
            for (Cart cart : carts) {
                orderBookService.saveOrderBook(newOrder.getId(), cart.getBookId());
                cartService.deleteCartByBookIdUserId(cart.getBookId(), userId);
            }
            newOrder.setPrice(((MapService) getServletContext().getAttribute("mapService")).convertToOrderBookDto(newOrder).getPriceAll());
            resp.sendRedirect("/cart");
        } else {
            resp.sendRedirect("/cart/confirmation");
        }
    }
}
