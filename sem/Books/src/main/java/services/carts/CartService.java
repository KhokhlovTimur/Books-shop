package services.carts;

import models.Book;
import models.Cart;
import models.User;

import java.util.List;
import java.util.Optional;

public interface CartService {
    public void saveBookToCart(Long bookId, Long userId);

    public Optional<Cart> findUserCartByUserId(User user);

    public List<Cart> findAllBooks(Long userId);

    public void deleteCartByBookIdUserId(Long bookId, Long userId);

}
