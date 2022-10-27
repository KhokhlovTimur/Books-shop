package services.utils;

import dao.utils.SearchDao;
import dto.BookDto;
import models.Author;
import models.Book;
import services.authors.AuthorService;
import services.books.BooksService;

import java.util.ArrayList;
import java.util.List;

public class SearchBooksService {
    private final BooksService booksService;
    private final AuthorService authorsService;
    private final SearchDao searchDao;

    public SearchBooksService(BooksService booksService, AuthorService authorService, SearchDao searchDao) {
        this.booksService = booksService;
        this.authorsService = authorService;
        this.searchDao = searchDao;
    }

    public List<BookDto> findBook(String input) {
        List<BookDto> bookDtoList = new ArrayList<>();
        List<Long> ids = searchDao.getSearchResult(input);
        if (ids.size() > 0) {
            for (Long id : ids) {
                bookDtoList.add(convertBookToBookDto(booksService.findBookById(id).get()));
            }
        }
        return bookDtoList;
    }


    private BookDto convertBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        Author author = authorsService.findAuthorById(book.getAuthorId()).get();
        bookDto.setId(book.getId());
        bookDto.setAuthorYearOfBirth(author.getBirthYear());
        bookDto.setAuthorName(author.getName());
        bookDto.setAuthorSurname(author.getSurname());
        bookDto.setTitle(book.getTitle());
        bookDto.setDescription(book.getDescription());
        bookDto.setPrice(book.getPrice());
        bookDto.setYearOfPublication(book.getYearOfPublication());
        return bookDto;
    }
}
