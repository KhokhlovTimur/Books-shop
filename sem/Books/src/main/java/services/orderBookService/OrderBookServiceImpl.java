package services.orderBookService;

import dao.OrderBookDao.OrderBookRepository;

public class OrderBookServiceImpl implements OrderBookService{
    private final OrderBookRepository orderBookRepository;
    public OrderBookServiceImpl(OrderBookRepository orderBookRepository){
        this.orderBookRepository = orderBookRepository;
    }

    @Override
    public void saveOrderBook(Long orderId, Long bookId) {
        orderBookRepository.saveOrderBook(orderId, bookId);
    }
}
