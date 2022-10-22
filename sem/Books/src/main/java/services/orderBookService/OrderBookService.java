package services.orderBookService;

import models.OrderBook;

import java.util.List;

public interface OrderBookService {
    public void saveOrderBook(Long orderId, Long bookId);

    public List<OrderBook> findAll();

    public boolean isBookExists(Long id);

}
