package services.orderService;

import models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public void saveOrder(Order order);

    public Optional<Order> findOrderById(Long id);

    public List<Order> findAll();
}
