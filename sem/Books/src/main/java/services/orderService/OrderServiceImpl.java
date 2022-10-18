package services.orderService;

import dao.ordersDao.OrderRepository;
import models.Order;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.saveOrder(order);
    }
}
