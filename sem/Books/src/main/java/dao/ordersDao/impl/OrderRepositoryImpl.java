package dao.ordersDao.impl;

import dao.ordersDao.OrderRepository;
import models.Order;
import providers.MyDriverManager;

import javax.xml.transform.Result;
import java.sql.*;

public class OrderRepositoryImpl implements OrderRepository {
    private final Connection connection = MyDriverManager.getConnection();
    //language=SQL
    private final static String SQL_SAVE_ORDER = "insert into orders (user_id, price) values (?, ?);";

    @Override
    public void saveOrder(Order order) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getPrice());

            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                throw new SQLException("Can't save user");
            }

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next()){
                order.setId(keys.getLong("id"));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
