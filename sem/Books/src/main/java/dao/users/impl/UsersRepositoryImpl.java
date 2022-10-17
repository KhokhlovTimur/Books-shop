package dao.users.impl;

import dao.users.UsersRepository;
import models.User;
import providers.MyDriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    private final Connection connection = MyDriverManager.getConnection();

    //language=SQL
    private static final String SQL_SAVE_USER = "insert into users(session_id, login, password, role) VALUES (?,?, ?, ?)";

    //language=SQL
    private static final String SQL_FIND_USER_BY_ID = "select * from users where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "select * from users";

    //language=SQL
    private static final String SQL_DELETE_USER_BY_ID = "delete from users where id = ?";

    //language=SQL
    private static final String SQL_UPDATE_USER_BY_ID = "update users set login=?, password=?, role=? where id=?";

    //language=SQL
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = "select * from users where login = ? and password = ?";

    //language=SQL
    private static final String SQL_FIND_BY_SESSION_ID = "select * from users where session_id = ?";

    @Override
    public void saveUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getSessionId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());

            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                throw new SQLException("Can't save user");
            }
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getLong("id"));
            } else {
                throw new SQLException();
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Optional<User> findUserById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {

            statement.setLong(1, id);
            statement.execute();

            ResultSet user = statement.executeQuery();
            if (user.next()) {
                return Optional.of(User.builder().id(user.getLong("id"))
                        .login(user.getString("login"))
                        .password(user.getString("password"))
                        .role(user.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserBySessionId(String id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_SESSION_ID)) {

            statement.setString(1, id);
            statement.execute();

            ResultSet user = statement.executeQuery();
            if (user.next()) {
                return Optional.of(User.builder().id(user.getLong("id"))
                        .sessionId(user.getString("session_id"))
                        .login(user.getString("login"))
                        .password(user.getString("password"))
                        .role(user.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD)) {

            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();

            ResultSet user = statement.executeQuery();
            if (user.next()) {
                return Optional.of(User.builder().id(user.getLong("id"))
                        .login(user.getString("login"))
                        .password(user.getString("password"))
                        .role(user.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    @Override
    public void deleteUserById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            try {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BY_ID)) {
            try {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole());
                preparedStatement.setLong(4, user.getId());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(User.builder().id(resultSet.getLong("id"))
                        .sessionId(resultSet.getString("session_id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return users;
    }
}
