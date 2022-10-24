package services.utils;

import dao.orderBookDao.OrderBookRepository;
import dao.authorsDao.AuthorsRepository;
import dao.booksDao.BooksRepository;
import dao.cartsDao.CartRepository;
import dao.ordersDao.OrderRepository;
import dto.BookDto;
import dto.OrderBookDto;
import models.Author;
import models.Book;
import models.Order;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapService {
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;
    private final CartRepository cartRepository;
    private final OrderBookRepository orderBookRepository;
    private final OrderRepository orderRepository;

    public MapService(BooksRepository booksRepository, AuthorsRepository authorsRepository, CartRepository cartRepository, OrderBookRepository orderBookRepository, OrderRepository orderRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.cartRepository = cartRepository;
        this.orderBookRepository = orderBookRepository;
        this.orderRepository = orderRepository;
    }

    public OrderBookDto convertToOrderBookDto(Order order){
        OrderBookDto orderBookDto = new OrderBookDto();
        orderBookDto.setOrderId(order.getId());
        orderBookDto.setBooks(convertToBookDtoFromOrderBook(order.getId()));
        orderBookDto.setPriceAll(order.getPrice());
        return orderBookDto;
    }

    public List<OrderBookDto> convertAllToOrderBookDto(Long userId){
        return orderRepository.findAll().stream()
                .filter(x-> Objects.equals(x.getUserId(), userId))
                .map(this::convertToOrderBookDto)
                .collect(Collectors.toList());
    }

    private List<BookDto> convertToBookDtoFromOrderBook(Long orderId){
        return orderBookRepository.findAll().stream()
                .filter(x-> Objects.equals(x.getOrderId(), orderId))
                .map(x->booksRepository.findBookById(x.getBookId()).get())
                .map(this::convertBookToBookDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> convertToBookDtoFromCart(Long userId){
        return cartRepository.findAllBooks(userId).stream()
                .map(x-> booksRepository.findBookById(x.getBookId()).get())
                .map(this::convertBookToBookDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> convertAllBooksToBooksDtoFromBooks() {
        return booksRepository.orderBooksById().stream()
                .map(this::convertBookToBookDto)
                .collect(Collectors.toList());
    }

    public BookDto convertBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        Author author = authorsRepository.findAuthorById(book.getAuthorId()).get();
        bookDto.setId(book.getId());
        bookDto.setAuthorYearOfBirth(author.getBirthYear());
        bookDto.setAuthorName(author.getName());
        bookDto.setAuthorSurname(author.getSurname());
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());
        bookDto.setYearOfPublication(book.getYearOfPublication());
        return bookDto;
    }
}
