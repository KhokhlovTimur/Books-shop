package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Author;
import models.Book;
import services.authors.AuthorService;
import services.books.BooksService;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import static java.util.Objects.nonNull;

@WebServlet("/admin/insert")
@MultipartConfig
public class InsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/insert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonValue = req.getParameter("save");
        if (buttonValue != null) {
            if (buttonValue.equals("book")) {
                String title = req.getParameter("title");
                String authorId = req.getParameter("authorId");
                String year = req.getParameter("year");
                String price = req.getParameter("price");
                if (nonNull(title) && nonNull(authorId)
                        && nonNull(year) && nonNull(price)
                        && ((AuthorService) getServletContext().getAttribute("authorsService")).findAuthorById(Long.valueOf(authorId)).isPresent()) {
                    Book book = Book.builder()
                            .title(title)
                            .authorId(Long.valueOf(authorId))
                            .yearOfPublication(Integer.parseInt(year))
                            .price(Integer.parseInt(price))
                            .build();
                    ((BooksService) getServletContext().getAttribute("booksService")).saveBook(book);
                    if (req.getPart("image").getInputStream() instanceof FileInputStream) {
                        try (InputStream inputStream = (req.getPart("image").getInputStream())) {
                            new File("..\\imagesForSite\\booksImages").mkdirs();
                            File file = new File(
                                    "..\\imagesForSite\\booksImages\\"
                                            + book.getId() + ".png");
                            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            throw new IllegalArgumentException();
                        }
                    }
                }
            } else if (buttonValue.equals("author")) {
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                String year = req.getParameter("year");
                if (nonNull(name) && nonNull(surname) && nonNull(year)) {
                    ((AuthorService) getServletContext().getAttribute("authorsService")).saveAuthor(Author.builder()
                            .name(name)
                            .surname(surname)
                            .birthYear(Integer.parseInt(year))
                            .build());
                }
            }
        }
        resp.sendRedirect("/admin/insert");
    }
}
