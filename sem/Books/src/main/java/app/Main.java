package app;

import dao.authorsDao.impl.AuthorsRepositoryImpl;
import dao.booksDao.BooksRepository;
import dao.booksDao.impl.BooksRepositoryImpl;
import dao.cartsDao.CartRepository;
import dao.cartsDao.impl.CartRepositoryImpl;
import dao.usersDao.impl.UsersRepositoryImpl;
import models.Book;
import models.User;
import services.authors.AuthorsServiceImpl;
import services.users.UsersServiceImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
//        System.out.println(new FileInputStream(new File()));
        BooksRepository booksRepository = new BooksRepositoryImpl();
        booksRepository.saveBook(Book.builder().description("123123").authorId(1L).yearOfPublication(1233).price(123).title("1").build());
    }

}
