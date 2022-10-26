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
//        System.out.println(search("и нэ п"));
//        System.out.println(new AuthorsSer/viceImpl(new AuthorsRepositoryImpl()).findAuthorBySurname("Кинг"));
        System.out.println(Long.valueOf("111111111111111111"));
//        booksRepository.save
//        Book(Book.builder().description("123123").authorId(1L).yearOfPublication(1233).price(123).title("1").build());
    }

//    private static final String SQL = "select books.id as id from books join authors a on a.id = books.author_id\n" +
//            "    join (select books.id as book_id, concat(title, ' ', authors.name, ' ', authors.surname) as names from books join authors  on books.author_id=authors.id) as tab on tab.book_id = books.id";
//    private static  String search(String input) {
//        StringBuilder select = new StringBuilder(SQL);
//        String[] inputSplit = input.split(" ");
//        List<String> bookInfo = Arrays.stream(inputSplit).filter(x -> x.length() > 0).collect(Collectors.toList());
//        if (bookInfo.size() > 1) {
//            select.append(" where names ");
//            for (String info : bookInfo) {
//                if (!info.chars().allMatch(Character::isDigit)) {
//                    select.append("ilike " + "'%").append(info).append("%'").append(" and names ");
//                } else {
//                    select.append("like " + "'%").append(info).append("%'").append(" and names ");
//                }
//            }
//            select.delete(select.length() - 10, select.length() - 1);
//        } else if (bookInfo.size() == 1) {
//            select.append(" where names ");
//            if (!bookInfo.get(0).chars().allMatch(Character::isDigit)) {
//                select.append("ilike " + "'%").append(bookInfo.get(0)).append("%'");
//            }
//            else {
//                select.append("like " + "'%").append(bookInfo.get(0)).append("%'");
//            }
//        }
//        return select.toString();
//    }
}
