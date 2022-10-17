package services;

import dao.books.BooksRepository;
import dto.AuthorDto;
import models.Author;
import models.Book;

import java.util.List;
import java.util.stream.Collectors;

public class MapService {
    private BooksRepository booksRepository;

    public MapService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<AuthorDto> convertAllAuthorsToAuthorDto() {
        return booksRepository.findAllBooks().stream()
                .map(this::convertAuthorToAuthorDto).collect(Collectors.toList());
    }

    public AuthorDto convertAuthorToAuthorDto(Book book) {
        AuthorDto authorDto = new AuthorDto();
        Author author = book.getAuthor();
        authorDto.setId(author.getId());
        authorDto.setBook(book);
        authorDto.setBirth_year(author.getBirthYear());
        authorDto.setName(author.getName());
        authorDto.setSurname(author.getSurname());
        return authorDto;
    }
}
