package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Author;
import models.Book;
import services.authors.AuthorService;
import services.books.BooksService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
            AuthorService authorService = ((AuthorService) getServletContext().getAttribute("authorsService"));
            switch (buttonValue) {
                case "book": {
                    String title = req.getParameter("title");
                    String authorId = req.getParameter("authorId");
                    String year = req.getParameter("year");
                    String price = req.getParameter("price");
                    String description = req.getParameter("descriptionBook");
                    if (nonNull(title) && nonNull(authorId) && nonNull(description)
                            && nonNull(year) && nonNull(price)
                            && authorService.findAuthorById(Long.valueOf(authorId)).isPresent()) {
                        Book book = Book.builder()
                                .title(title)
                                .authorId(Long.valueOf(authorId))
                                .yearOfPublication(Integer.parseInt(year))
                                .price(Integer.parseInt(price))
                                .description(description)
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
                    break;
                }
                case "author": {
                    String name = req.getParameter("name");
                    String surname = req.getParameter("surname");
                    String year = req.getParameter("year");
                    if (nonNull(name) && nonNull(surname) && nonNull(year)) {
                        authorService.saveAuthor(Author.builder()
                                .name(name)
                                .surname(surname)
                                .birthYear(Integer.parseInt(year))
                                .build());
                    }
                    break;
                }
                case "updateAuthor": {
                    String name = req.getParameter("updateName");
                    String id = req.getParameter("updateId");
                    String surname = req.getParameter("updateSurname");
                    String year = req.getParameter("updateYear");

                    if (nonNull(name) && nonNull(surname) && nonNull(year) && nonNull(id)
                            && authorService.findAuthorById(Long.valueOf(id)).isPresent()) {
                        authorService.updateAuthor(Author.builder()
                                .id(Long.valueOf(id))
                                .name(name)
                                .surname(surname)
                                .birthYear(Integer.parseInt(year))
                                .build());
                    }
                    break;
                }
            }
        }
        resp.sendRedirect("/admin/insert");
    }
}
