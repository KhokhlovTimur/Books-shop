package services.users.impl;

import dao.usersDao.UsersRepository;
import models.User;
import services.users.UsersService;

import java.util.List;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {
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
        for(User user: usersRepository.findAllUsers()){
            if(user.getLogin() != null && user.getPassword() != null && user.getLogin().equals(login) && user.getPassword().equals(password)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
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
    public Optional<User> findUserByLogin(String login) {
        for(User user: usersRepository.findAllUsers()){
            if(user.getLogin() != null && user.getLogin().equals(login)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
