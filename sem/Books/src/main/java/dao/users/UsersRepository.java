package dao.users;

import models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    public void saveUser(User user);

    public Optional<User> findUserById(Long id);

    public Optional<User> findUserByLoginAndPassword(String login, String password);

    public void deleteUserById(Long id);

    public void updateUser(User user);

    public List<User> findAllUsers();

    public Optional<User> findUserBySessionId(String id);
}
