package dao.usersDao.impl;

import dao.usersDao.UsersRepository;
import models.User;
import providers.MyDriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UsersRepositoryImpl implements UsersRepository {
    private final Connection connection = MyDriverManager.getConnection();

    //language=SQL
    private static final String SQL_SAVE_USER = "insert into users(session_id, login, password, role) VALUES (?,?, ?, ?)";

    //language=SQL
    private static final String SQL_FIND_USER_BY_ID = "select * from users where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "select * from users order by id";

    //language=SQL
    private static final String SQL_DELETE_USER_BY_ID = "delete from users where id = ?";

    //language=SQL
    private static final String SQL_UPDATE_USER_BY_ID = "update users set login=?, password=?, role=?, session_id=? where id=?";

    private final static Function<ResultSet, User> userMapper = row ->{
        try {
            return User.builder().id(row.getLong("id"))
                    .sessionId(row.getString("session_id"))
                    .role(row.getString("role"))
                    .login(row.getString("login"))
                    .password( row.getString("password"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };
    @Override
    public void saveUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
//            connection.setAutoCommit(false);
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
                return Optional.of(userMapper.apply(user));
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
                preparedStatement.setString(4, user.getSessionId());
                preparedStatement.setLong(5, user.getId());
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
                users.add(userMapper.apply(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return users;
    }

}
