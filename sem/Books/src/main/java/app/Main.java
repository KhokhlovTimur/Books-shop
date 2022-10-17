package app;

import dao.books.impl.BooksRepositoryImpl;

public class Main {
    public static void main(String[] args) {
//        UsersRepository usersRepository = new UsersRepositoryImpl();
//        new BooksRepositoryImpl().saveBook(Book.builder().id(100L).tittle("3").authorId(1L).yearOfPublication(1999).price(404).build());
//        usersRepository.saveUser(User.builder().login("12").password("21").role("123").build());
//        System.out.println(usersRepository.findUserById(2L));
//        System.out.println("".length());
//        usersRepository.findUserBySessionId(null);
        System.out.println(new BooksRepositoryImpl().findBookById(4L));
    }
}
