package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import org.w3c.dom.ls.LSOutput;
import services.carts.CartService;

import java.io.IOException;

@WebServlet("/menu")
public class MainMenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sortBy = req.getParameter("sortBy");
        String isSorted = req.getParameter("isSorted");;
        if(isSorted != null && sortBy != null){
            req.setAttribute("sortBy", sortBy);
        }
        String search = req.getParameter("poisk");
        String input = req.getParameter("search");
        if(search != null && input != null){
            req.setAttribute("poisk", input);
            System.out.println(1);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartBook = req.getParameter("toCart");
        User user = (User) req.getSession().getAttribute("user");
        CartService cartService = (CartService) getServletContext().getAttribute("cartService");
        if(cartBook != null && user != null && !user.getRole().equals("noAuth")){
            cartService.saveBookToCart(Long.valueOf(cartBook), user.getId());
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(req, resp);
    }
}
