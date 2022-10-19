package services.carts;

import dao.cartsDao.CartRepository;
import models.Cart;
import models.User;

import java.util.List;
import java.util.Optional;

public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    public void saveBookToCart(Long bookId, Long userId) {
        cartRepository.saveBookToCart(bookId, userId);
    }

    @Override
    public Optional<Cart> findUserCartByUserId(User user) {
        return cartRepository.findUserCartByUserId(user);
    }

    @Override
    public List<Cart> findAllBooks(Long userId) {
        return cartRepository.findAllBooks(userId);
    }

    @Override
    public void deleteCartByBookIdUserId(Long bookId, Long userId) {
        cartRepository.deleteCartByBookIdUserId(bookId, userId );
    }

    @Override
    public int getUserCartPrice(Long userId) {
        return 0;
    }
}
