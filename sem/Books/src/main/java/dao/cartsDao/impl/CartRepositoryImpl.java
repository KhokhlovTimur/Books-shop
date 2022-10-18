package dao.cartsDao.impl;

import dao.cartsDao.CartRepository;
import models.Cart;
import models.User;
import providers.MyDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartRepositoryImpl implements CartRepository {
    private final Connection connection = MyDriverManager.getConnection();

    //language=SQL
    private final static String SQL_SAVE_BOOK_TO_CART = "insert into cart (id, user_id, book_id) values (?, ?, ?);";

    //language=SQL
    private final static String SQL_FIND_ALL_BOOKS = "select * from cart where user_id = ?";

    //language=SQL
    private final static String SQL_DELETE_CART_BY_BOOK_ID_AND_USER_ID = "delete from cart where book_id = ? and user_id = ?";

    @Override
    public void saveBookToCart(Long bookId, Long userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_BOOK_TO_CART)) {

            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, bookId);

            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                throw new SQLException("Can't save user");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Optional<Cart> findUserCartByUserId(User user) {
        return Optional.empty();
    }

    public List<Cart> findAllBooks(Long userId) {
        List<Cart> bookList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_BOOKS)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookList.add(Cart.builder().id(resultSet.getLong("id"))
                        .bookId(resultSet.getLong("book_id"))
                        .userId(resultSet.getLong("user_id"))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return bookList;
    }

    @Override
    public void deleteCartByBookIdUserId(Long bookId, Long userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CART_BY_BOOK_ID_AND_USER_ID)) {
            try {
                preparedStatement.setLong(1, bookId);
                preparedStatement.setLong(2, userId);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
