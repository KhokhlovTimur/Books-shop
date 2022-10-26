package services.books;

import models.Author;
import models.Book;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    public void saveBook(Book book);

    public Optional<Book> findBookById(Long id);

    public void deleteBookById(Long id);

    public void updateBook(Book book);

    public List<Book> findAllBooks();


    public void updateBookWithIncompleteParameters(Book book, String title, String price, String year, String authorId, String description);

    public List<Book> orderBooksById();
}
