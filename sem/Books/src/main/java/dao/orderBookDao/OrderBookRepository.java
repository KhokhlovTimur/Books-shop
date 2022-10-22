package dao.orderBookDao;

import models.OrderBook;

import java.util.List;

public interface OrderBookRepository {
    public void saveOrderBook(Long orderId, Long bookId);

    public List<OrderBook> findAll();
}
