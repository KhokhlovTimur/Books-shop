package dao.OrderBookDao;

import models.Book;
import models.OrderBook;
import providers.MyDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OrderBookRepositoryImpl implements OrderBookRepository {
    //language=SQL
    private final static String SQL_SAVE_ORDER_BOOK = "insert into order_book(order_id, book_id) values (?, ?)";

    //language=SQL
    private final static String SQL_FIND_ALL_ORDER_BOOK = "select * from order_book";

    private final static Function<ResultSet, OrderBook> orderBookMapper = row ->{
        try {
            return OrderBook.builder().bookId(row.getLong("book_id")).orderId(row.getLong("order_id")).build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };


    public void saveOrderBook(Long orderId, Long bookId) {
        try (Connection connection = MyDriverManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE_ORDER_BOOK)) {
                statement.setLong(1, orderId);
                statement.setLong(2, bookId);

                int affectedRows = statement.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Can't save");
                }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<OrderBook> findAll() {
        List<OrderBook> books = new ArrayList<>();
        try (PreparedStatement statement = MyDriverManager.getConnection().prepareStatement(SQL_FIND_ALL_ORDER_BOOK)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(orderBookMapper.apply(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return books;
    }
}
