package services.books;

import dao.booksDao.BooksRepository;
import models.Author;
import models.Book;

import java.util.List;
import java.util.Optional;

public class BooksServiceImpl implements BooksService {
    private final BooksRepository booksRepository;

    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public void saveBook(Book book) {
        booksRepository.saveBook(book);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return booksRepository.findBookById(id);
    }

    @Override
    public void deleteBookById(Long id) {
        booksRepository.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        booksRepository.updateBook(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return booksRepository.findAllBooks();
    }

    @Override
    public Optional<Author> findBookAuthorByAuthorId(Long id) {
        return null;
    }
}