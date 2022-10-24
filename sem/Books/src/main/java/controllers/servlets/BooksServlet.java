package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.books.BooksService;
import services.orderBookService.OrderBookService;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@WebServlet("/admin/books")
@MultipartConfig
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
        String description = req.getParameter("description");

        if (bookID != null && booksService.findBookById(Long.valueOf(bookID)).isPresent()) {
            booksService.updateBookWithIncompleteParameters(booksService.findBookById(Long.valueOf(bookID)).get(), title, price, year, authorId, description);
            if (req.getPart("image").getInputStream() instanceof FileInputStream) {
                try (InputStream inputStream = (req.getPart("image").getInputStream())) {
                    new File("..\\imagesForSite\\booksImages").mkdirs();
                    File file = new File(
                            "..\\imagesForSite\\booksImages\\"
                                    + bookID + ".png");
                    Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new IllegalArgumentException();
                }
            }
        }

        resp.sendRedirect("/admin/books");
    }
}
