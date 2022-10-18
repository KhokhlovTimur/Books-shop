package controllers.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebListener
public class CartProductsListener implements HttpSessionAttributeListener {
    private static final Map<String, List<Long>> CART = new HashMap<>();

    public static List<Long> putToCart(Long userId) {
        List<Long> cart = CART.get(userId);
        if (cart != null) {
            return cart;
        }
        return Collections.emptyList();
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeRemoved(event);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeReplaced(event);
    }
}
