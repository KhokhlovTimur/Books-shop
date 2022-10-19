package app;

import dao.authorsDao.impl.AuthorsRepositoryImpl;
import dao.cartsDao.CartRepository;
import dao.cartsDao.impl.CartRepositoryImpl;
import dao.usersDao.impl.UsersRepositoryImpl;
import services.authors.AuthorsServiceImpl;
import services.users.UsersServiceImpl;

public class Main {
    public static void main(String[] args) {
//        UsersRepository usersRepository = new UsersRepositoryImpl();
//        new BooksRepositoryImpl().saveBook(Book.builder().id(100L).tittle("3").authorId(1L).yearOfPublication(1999).price(404).build());
//        usersRepository.saveUser(User.builder().login("12").password("21").role("123").build());
        CartRepository cartRepository = new CartRepositoryImpl();
//    cartRepository.saveBookToCart(1L,1L);
        UsersServiceImpl usersService = new UsersServiceImpl(new UsersRepositoryImpl());
        usersService.findUserByLoginAndPassw(null, "1");
        usersService.findUserBySessionId(null);

//        System.out.println(cartRepository.findAllBooks(1L));
////        System.out.println(new BooksRepositoryImpl().findBookById(4L));
//        AuthorsServiceImpl authorsService = new AuthorsServiceImpl(new AuthorsRepositoryImpl());
//        System.out.println(authorsService.findAuthorById(100L));
    }
}
