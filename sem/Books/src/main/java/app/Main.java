package app;

import dao.authorsDao.impl.AuthorsRepositoryImpl;
import dao.booksDao.impl.BooksRepositoryImpl;
import dto.BookDto;
import models.Author;
import models.Book;
import services.authors.impl.AuthorsServiceImpl;
import services.books.impl.BooksServiceImpl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
//
//        try (BufferedInputStream inputStream = new BufferedInputStream(
//                new URL("https://yandex.ru/images/search?text=dark%20tower&from=tabbar&pos=15&img_url=http%3A%2F%2Fa.d-cd.net%2F3917b99s-1920.jpg&rpt=simage&lr=43").openStream())) {
//            System.out.println(new File("file").mkdirs());
////            File file = new File("C:\\Users\\Пользователь\\Desktop\\semestr\\Semester-work\\sem\\Books\\src\\main\\webapp\\images\\bookImages\\" + 1 + ".jpg");
////            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new IllegalArgumentException();
//        }
//        System.out.println(findBook("Кинг"));
        System.out.println(search("1212 1221 adasd sadas"));
//        System.out.println(new AuthorsSer/viceImpl(new AuthorsRepositoryImpl()).findAuthorBySurname("Кинг"));

//        System.out.println("Кинг".toLowerCase().equals("киНг".toLowerCase()));

//        booksRepository.saveBook(Book.builder().description("123123").authorId(1L).yearOfPublication(1233).price(123).title("1").build());
    }

    public static List<BookDto> findBook(String input) {
        BooksServiceImpl booksService = new BooksServiceImpl(new BooksRepositoryImpl());
        String[] inputSplit = input.split(" ");
        List<BookDto> result = new ArrayList<>();
        List<String> bookInfo = Arrays.stream(inputSplit).filter(x -> x.length() > 0).collect(Collectors.toList());
        if (bookInfo.size() == 1) {
            for (String info : bookInfo) {
                List<Book> books = new BooksServiceImpl(new BooksRepositoryImpl()).findBookByFullTitle(info);
                List<Author> authorsName = new AuthorsServiceImpl(new AuthorsRepositoryImpl()).findAuthorByName(info);
                List<Author> authorsSurname = new AuthorsServiceImpl(new AuthorsRepositoryImpl()).findAuthorBySurname(info);

                if (books.size() > 0 || authorsName.size() > 0 || authorsSurname.size() > 0) {
                    if (authorsName.size() > 0) {
                        for (Author author : authorsName) {
                            List<BookDto> booksList = (booksService.findAllBooks().stream()
                                    .filter(x -> Objects.equals(x.getAuthorId(), author.getId())).map(Main::convertBookToBookDto).collect(Collectors.toList()));
                            result.addAll(booksList);

                        }
                    }
                    else if(authorsSurname.size() > 0){
                        for (Author author : authorsSurname) {
                            List<BookDto> booksList = (booksService.findAllBooks().stream()
                                    .filter(x -> Objects.equals(x.getAuthorId(), author.getId())).map(Main::convertBookToBookDto).collect(Collectors.toList()));
                            result.addAll(booksList);

                        }
                    }
                    else {
                        result.addAll(books.stream().map(Main::convertBookToBookDto).collect(Collectors.toList()));
                    }
                }
            }
        }
//        else if(bookInfo.size() == 2){
//
//        }
        return result;
    }
    private static BookDto convertBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        Author author = new AuthorsServiceImpl(new AuthorsRepositoryImpl()).findAuthorById(book.getAuthorId()).get();
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

    public static String search(String input) {
        String[] inputSplit = input.split(" ");
        List<String> bookInfo = Arrays.stream(inputSplit).filter(x -> x.length() > 0).collect(Collectors.toList());
        if (bookInfo.size() > 1) {
            for (String info : bookInfo) {
                SQL.append("like " + "'%").append(info).append("%").append(" and ");
            }
        }
        else if(bookInfo.size() == 1){
            SQL.append("like " + "'%").append(bookInfo.get(0)).append("%");
        }
        else {

        }
        SQL.deleteCharAt(SQL.length() - 1).deleteCharAt(SQL.length() - 1).deleteCharAt(SQL.length() - 1).deleteCharAt(SQL.length() - 1);
        return SQL.toString();
    }
    private static StringBuilder SQL = new StringBuilder("select  books.title as title, a.name as a_name, a.surname as a_surname from books join authors a on a.id = books.author_id\n" +
            "    join (select books.id as book_id, concat(title, ' ', authors.name, ' ', authors.surname) as names from books join authors  on books.author_id=authors.id) as tab on tab.book_id = books.id\n" +
            "where names ");

}
