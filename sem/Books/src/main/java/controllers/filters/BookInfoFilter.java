package controllers.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.books.BooksService;

import java.io.IOException;

@WebFilter("/menu/bookInfo")
public class BookInfoFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String id = req.getParameter("bookId");
        if(id != null && ((BooksService)getServletContext().getAttribute("booksService")).findBookById(Long.valueOf(id)).isPresent()){
            req.setAttribute("bookId", id);
            chain.doFilter(req, res);
        }
        else {
            res.sendRedirect("/menu");
        }
    }
}
