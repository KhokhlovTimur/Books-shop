package services.orderBookService;

import dao.orderBookDao.OrderBookRepository;
import models.OrderBook;

import java.util.List;
import java.util.Objects;

public class OrderBookServiceImpl implements OrderBookService{
    private final OrderBookRepository orderBookRepository;
    public OrderBookServiceImpl(OrderBookRepository orderBookRepository){
        this.orderBookRepository = orderBookRepository;
    }

    @Override
    public void saveOrderBook(Long orderId, Long bookId) {
        orderBookRepository.saveOrderBook(orderId, bookId);
    }

    @Override
    public List<OrderBook> findAll() {
        return orderBookRepository.findAll();
    }

    @Override
    public boolean isBookExists(Long id) {
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        for (OrderBook orderBook: orderBookList) {
            if(Objects.equals(orderBook.getBookId(), id)){
                return true;
            }
        }
        return false;
    }
}
