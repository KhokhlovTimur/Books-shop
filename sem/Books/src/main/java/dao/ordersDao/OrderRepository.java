package dao.ordersDao;

import models.Order;

public interface OrderRepository {
    public void saveOrder(Order order);

}
