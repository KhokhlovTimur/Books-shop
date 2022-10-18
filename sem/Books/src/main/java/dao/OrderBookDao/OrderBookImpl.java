package dao.OrderBookDao;

import providers.MyDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderBookImpl implements OrderBook{
    //language=SQL
    private final static String SQL_SAVE_ORDER_BOOK = "insert into order_book(order_id, book_id) values (?, ?)";

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
}
