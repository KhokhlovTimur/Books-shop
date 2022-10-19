package services.utils;

import dao.booksDao.BooksRepository;
import dao.cartsDao.CartRepository;
import models.Cart;

public class CartSumService {
    private final CartRepository cartRepository;
    private final BooksRepository booksRepository;

    public CartSumService(CartRepository cartRepository, BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
        this.cartRepository = cartRepository;
    }

    public int getCartSumByUserId(Long userId) {
        int sum = 0;
        for (Cart cart : cartRepository.findAllBooks(userId)) {
            sum += booksRepository.findBookById(cart.getBookId()).get().getPrice();
        }
        return sum;
    }
}
