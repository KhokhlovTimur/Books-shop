package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.books.BooksService;
import services.orderBookService.OrderBookService;

import java.io.IOException;

@WebServlet("/admin/books")
public class BooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] deleteIDs = req.getParameterValues("checkBookId");
        if (deleteIDs != null) {
            for (String id : deleteIDs) {
                if (!((OrderBookService) getServletContext().getAttribute("orderBookService")).isBookExists(Long.valueOf(id))) {
                    ((BooksService) getServletContext().getAttribute("booksService")).deleteBookById(Long.valueOf(id));
                }
            }
        }

        BooksService booksService = (BooksService) getServletContext().getAttribute("booksService");
        String bookID = req.getParameter("id");
        String title = req.getParameter("title");
        String price = req.getParameter("price");
        String year = req.getParameter("year");
        String authorId = req.getParameter("authorId");
        if (bookID != null && booksService.findBookById(Long.valueOf(bookID)).isPresent()) {
            booksService.updateBookWithIncompleteParameters(booksService.findBookById(Long.valueOf(bookID)).get(), title, price, year, authorId);
        }

        resp.sendRedirect("/admin/books");
    }
}
