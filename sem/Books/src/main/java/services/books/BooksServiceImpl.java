package services.books;

import dao.booksDao.BooksRepository;
import lombok.Lombok;
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
        if (booksRepository.findBookById(id).isPresent()) {
            booksRepository.deleteBookById(id);
        }
    }

    @Override
    public void updateBook(Book book) {
        booksRepository.updateBook(book);

    }

    @Override
    public List<Book> findAllBooks() {
        return booksRepository.findAllBooks();
    }

//    @Override
//    public Optional<Author> findBookAuthorByAuthorId(Long id) {
//        return booksRepository.;
//    }

    @Override
    public void updateBookWithIncompleteParameters(Book book, String title, String price, String year, String authorId, String descript) {
        Book newBook = new Book();

        newBook.setId(book.getId());
        newBook.setTitle(title != null && title.length() > 0? title : book.getTitle());
        newBook.setPrice(price != null && price.length() > 0 ? Integer.parseInt(price) : book.getPrice());
        newBook.setAuthorId(authorId != null && authorId.length() > 0 ?  Long.valueOf(authorId):  book.getAuthorId());
        newBook.setYearOfPublication(year != null && year.length() > 0?  Integer.parseInt(year) :  book.getYearOfPublication());
        newBook.setDescription(descript != null ? descript : book.getDescription());

        this.booksRepository.updateBook(newBook);
    }

    @Override
    public List<Book> orderBooksById() {
        return booksRepository.orderBooksById();
    }
}