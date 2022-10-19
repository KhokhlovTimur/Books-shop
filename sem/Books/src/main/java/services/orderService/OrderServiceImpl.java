package services.orderService;

import dao.ordersDao.OrderRepository;
import models.Order;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.saveOrder(order);
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
