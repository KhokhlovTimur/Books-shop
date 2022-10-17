package app;

import dao.users.UsersRepository;
import dao.users.impl.UsersRepositoryImpl;
import models.User;

public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepositoryImpl();
//        usersRepository.saveUser(User.builder().login("12").password("21").role("123").build());
//        System.out.println(usersRepository.findUserById(2L));
//        System.out.println("".length());
        usersRepository.findUserBySessionId(null);
    }
}
