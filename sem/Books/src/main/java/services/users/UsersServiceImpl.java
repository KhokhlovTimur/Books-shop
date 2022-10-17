package services.users;

import dao.users.UsersRepository;
import models.User;

import java.util.List;
import java.util.Optional;

public class UsersServiceImpl implements UsersService{
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public void saveUser(User user) {
        usersRepository.saveUser(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return usersRepository.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        usersRepository.updateUser(user);
    }

    @Override
    public Optional<User> findUserByLoginAndPassw(String login, String password) {
        return usersRepository.findUserByLoginAndPassword(login, password);
    }

    @Override
    public List<User> findAllUsers() {
        return usersRepository.findAllUsers();
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteUserById(id);
    }

    @Override
    public Optional<User> findUserBySessionId(String id) {
        return usersRepository.findUserBySessionId(id);
    }
}
