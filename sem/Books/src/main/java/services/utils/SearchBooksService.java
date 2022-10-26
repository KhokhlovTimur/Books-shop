package services.utils;

import app.Main;
import dao.authorsDao.AuthorsRepository;
import dao.authorsDao.impl.AuthorsRepositoryImpl;
import dao.booksDao.BooksRepository;
import dao.booksDao.impl.BooksRepositoryImpl;
import dto.BookDto;
import models.Author;
import models.Book;
import services.authors.AuthorService;
import services.authors.impl.AuthorsServiceImpl;
import services.books.BooksService;
import services.books.impl.BooksServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

public class SearchBooksService {
    private BooksService booksService;
    private AuthorService authorsService;

    public SearchBooksService(BooksService booksService, AuthorService authorService) {
        this.booksService = booksService;
        this.authorsService = authorService;
    }

    public List<BookDto> findBook(String input) {
//        String[] inputSplit = input.split(" ");
//        List<BookDto> result = new ArrayList<>();
//        List<String> bookInfo = Arrays.stream(inputSplit).filter(x -> x.length() > 0).collect(Collectors.toList());
//        StringBuilder info = new StringBuilder();
//        for(int i = 0; i < bookInfo.size(); i++){
//
//        }
        if (booksService.findBookByFullTitle(input).size() > 0) {
            return booksService.findBookByFullTitle(input).stream()
                    .map(this::convertBookToBookDto).collect(Collectors.toList());
        } else if (authorsService.findAuthorByName(input).size() > 0) {
            for (Author author : authorsService.findAuthorByName(input)) {
                return booksService.findAllBooks().stream()
                        .filter(x -> Objects.equals(x.getAuthorId(), author.getId())).map(this::convertBookToBookDto).collect(Collectors.toList());
            }
        }
        else if(authorsService.findAuthorBySurname(input).size() > 0){
            for (Author author : authorsService.findAuthorBySurname(input)) {
                return (booksService.findAllBooks().stream()
                        .filter(x -> Objects.equals(x.getAuthorId(), author.getId())).map(this::convertBookToBookDto).collect(Collectors.toList()));
            }
        }
//        List<Author> authorsName = authorsService.findAuthorByName(info);
//        List<Author> authorsSurname = authorsService.findAuthorBySurname(info);


//        if (bookInfo.size() == 1) {
//            for (String info : bookInfo) {
//                List<Book> books = booksService.findBookByFullTitle(info);
//                List<Author> authorsName = authorsService.findAuthorByName(info);
//                List<Author> authorsSurname = authorsService.findAuthorBySurname(info);
//
//                if (books.size() > 0 || authorsName.size() > 0 || authorsSurname.size() > 0) {
//                    if (authorsName.size() > 0) {
//                        for (Author author : authorsName) {
//                            List<BookDto> booksList = (booksService.findAllBooks().stream()
//                                    .filter(x -> Objects.equals(x.getAuthorId(), author.getId())).map(this::convertBookToBookDto).collect(Collectors.toList()));
//                            result.addAll(booksList);
//
//                        }
//                    }
//                    else if(authorsSurname.size() > 0){
//                        for (Author author : authorsSurname) {
//                            List<BookDto> booksList = (booksService.findAllBooks().stream()
//                                    .filter(x -> Objects.equals(x.getAuthorId(), author.getId())).map(this::convertBookToBookDto).collect(Collectors.toList()));
//                            result.addAll(booksList);
//
//                        }
//                    }
//                    else {
//                        result.addAll(books.stream().map(this::convertBookToBookDto).collect(Collectors.toList()));
//                    }
//                }
//            }
//        }
        return Collections.emptyList();
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
