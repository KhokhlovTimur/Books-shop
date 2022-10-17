package services;

import dao.authors.AuthorsRepository;
import dao.books.BooksRepository;
import dto.BookDto;
import models.Author;
import models.Book;

import java.util.List;
import java.util.stream.Collectors;

public class MapService {
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;

    public MapService(BooksRepository booksRepository, AuthorsRepository authorsRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
    }

    public List<BookDto> convertAllAuthorsToAuthorDto() {
        return booksRepository.findAllBooks().stream()
                .map(this::convertBookToBookDto).collect(Collectors.toList());
    }

    public BookDto convertBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        Author author = authorsRepository.findAuthorById(book.getAuthorId()).get();
        bookDto.setId(book.getId());
        bookDto.setAuthorYearOfBirth(author.getBirthYear());
        bookDto.setAuthorName(author.getName());
        bookDto.setAuthorSurname(author.getSurname());
        bookDto.setTittle(book.getTittle());
        bookDto.setPrice(book.getPrice());
        bookDto.setYearOfPublication(book.getYearOfPublication());
        return bookDto;
    }
}
