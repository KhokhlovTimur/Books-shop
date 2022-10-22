package dao.ordersDao.impl;

import dao.ordersDao.OrderRepository;
import models.Book;
import models.Order;
import models.OrderBook;
import providers.MyDriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OrderRepositoryImpl implements OrderRepository {
    private final Connection connection = MyDriverManager.getConnection();
    //language=SQL
    private final static String SQL_SAVE_ORDER = "insert into orders (user_id, price) values (?, ?);";

    //language=SQL
    private final static String SQL_FIND_ORDER_BY_ID = "select * from orders where id = ?";

    //language=SQL
    private final static String SQL_FIND_ALL_ORDERS = "select * from orders order by id";

    private final static Function<ResultSet, Order> orderMapper = row ->{
        try {
            return Order.builder().id(row.getLong("id")).userId(row.getLong("user_id")).price(row.getLong("price")).build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

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
            if (keys.next()) {
                order.setId(keys.getLong("id"));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)) {

            statement.setLong(1, id);
            statement.execute();

            ResultSet order = statement.executeQuery();
            if (order.next()) {
                return Optional.of(orderMapper.apply(order));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(orderMapper.apply(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return orders;
    }


}
