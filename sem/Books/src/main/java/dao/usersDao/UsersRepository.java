package dao.usersDao;

import models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    public void saveUser(User user);

    public Optional<User> findUserById(Long id);

    public void deleteUserById(Long id);

    public void updateUser(User user);

    public List<User> findAllUsers();

}