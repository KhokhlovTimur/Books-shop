package services.orderBookService;

import dao.OrderBookDao.OrderBook;
import dao.ordersDao.OrderRepository;

public class OrderBookServiceImpl implements OrderBookService{
    private final OrderBook orderBook;
    public OrderBookServiceImpl(OrderBook orderBook){
        this.orderBook = orderBook;
    }

    @Override
    public void saveOrderBook(Long orderId, Long bookId) {
        orderBook.saveOrderBook(orderId, bookId);
    }
}
