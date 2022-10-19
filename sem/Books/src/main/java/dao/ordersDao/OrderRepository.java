package dao.ordersDao;

import models.Order;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    public void saveOrder(Order order);

    public Optional<Order> findOrderById(Long id);

    public List<Order> findAll();

}
