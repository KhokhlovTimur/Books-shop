package services;

import dao.authorsDao.AuthorsRepository;
import dao.booksDao.BooksRepository;
import dao.cartsDao.CartRepository;
import dto.BookDto;
import models.Author;
import models.Book;

import java.util.List;
import java.util.stream.Collectors;

public class MapService {
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;
    private final CartRepository cartRepository;

    public MapService(BooksRepository booksRepository, AuthorsRepository authorsRepository, CartRepository cartRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.cartRepository = cartRepository;
    }

    public List<BookDto> convertToBookDto(Long userId){
        return cartRepository.findAllBooks(userId).stream().map(x-> booksRepository.findBookById(x.getBookId()).get()).map(this::convertBookToBookDto).collect(Collectors.toList());
    }

    public List<BookDto> convertAllAuthorsToAuthorDto() {
        return booksRepository.findAllBooks().stream()
                .map(this::convertBookToBookDto).collect(Collectors.toList());
    }

    private BookDto convertBookToBookDto(Book book) {
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
