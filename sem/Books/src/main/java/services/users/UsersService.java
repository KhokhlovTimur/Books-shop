package services.users;

import models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    public void saveUser(User user);

    public Optional<User> findUserById(Long id);

    public void updateUser(User user);

    public Optional<User> findUserByLoginAndPassw(String login, String password);

    public List<User> findAllUsers();

    public void deleteUser(Long id);

    public Optional<User> findUserBySessionId(String id);
}
