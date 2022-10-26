package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cart;
import models.Order;
import models.User;
import services.carts.impl.CartServiceImpl;
import services.orderBookService.impl.OrderBookServiceImpl;
import services.orderService.OrderService;
import services.users.UsersService;
import services.utils.CartSumService;
import services.utils.HashConverter;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart/confirmation")
public class ConfirmationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/confirmation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("confirmPassword");
        User user = ((User) req.getSession().getAttribute("user"));

        OrderBookServiceImpl orderBookService = ((OrderBookServiceImpl) getServletContext().getAttribute("orderBookService"));
        CartServiceImpl cartService = (CartServiceImpl) getServletContext().getAttribute("cartService");
        CartSumService cartSumService = ((CartSumService) getServletContext().getAttribute("cartSumService"));
        UsersService usersService = (UsersService) getServletContext().getAttribute("usersService");

        if (password != null && HashConverter.hashPassword(password).equals(user.getPassword())) {
            List<Cart> carts = cartService.findAllBooks(user.getId());
            if (carts.size() > 0) {
                if (cartSumService.getCartSumByUserId(user.getId()) <= user.getBalance()) {
                    Order newOrder = Order.builder()
                            .userId(user.getId())
                            .price((long) cartSumService.getCartSumByUserId(user.getId()))
                            .build();
                    ((OrderService) getServletContext().getAttribute("orderService")).saveOrder(newOrder);

                    user.setBalance(user.getBalance() - cartSumService.getCartSumByUserId(user.getId()));
                    usersService.updateUser(user);
                    req.getSession().setAttribute("user", user);

                    for (Cart cart : carts) {
                        orderBookService.saveOrderBook(newOrder.getId(), cart.getBookId());
                        cartService.deleteCartByBookIdUserId(cart.getBookId(), user.getId());
                    }
                }
            }
            resp.sendRedirect("/cart");
        } else {
            resp.sendRedirect("/cart/confirmation");
        }
    }
}
