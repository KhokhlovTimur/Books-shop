package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.books.BooksService;
import services.carts.CartService;

import java.io.IOException;

@WebServlet("/menu/bookInfo")
public class BookInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("bookId");
        if(id != null && ((BooksService)getServletContext().getAttribute("booksService")).findBookById(Long.valueOf(id)).isPresent()){
            req.setAttribute("bookId", id);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/bookInfo.jsp").forward(req, resp);
        }
        else {
            resp.sendRedirect("/menu");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartBook = req.getParameter("toCart");
        User user = (User) req.getSession().getAttribute("user");
        CartService cartService = (CartService) getServletContext().getAttribute("cartService");
        if(cartBook != null && user != null && !user.getRole().equals("noAuth")){
            cartService.saveBookToCart(Long.valueOf(cartBook), user.getId());
        }
        req.setAttribute("bookId", cartBook);
        resp.sendRedirect("/menu/bookInfo");
    }
}
