package app;

import dao.cartsDao.CartRepository;
import dao.cartsDao.impl.CartRepositoryImpl;

public class Main {
    public static void main(String[] args) {
//        UsersRepository usersRepository = new UsersRepositoryImpl();
//        new BooksRepositoryImpl().saveBook(Book.builder().id(100L).tittle("3").authorId(1L).yearOfPublication(1999).price(404).build());
//        usersRepository.saveUser(User.builder().login("12").password("21").role("123").build());
        CartRepository cartRepository = new CartRepositoryImpl();
//    cartRepository.saveBookToCart(1L,1L);
        System.out.println(cartRepository.findAllBooks(1L));
//        System.out.println(new BooksRepositoryImpl().findBookById(4L));
    }
}
