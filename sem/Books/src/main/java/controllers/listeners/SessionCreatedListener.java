package controllers.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionCreatedListener implements HttpSessionListener {
    private static final AtomicInteger ACTIVE_SESSIONS = new AtomicInteger();

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ACTIVE_SESSIONS.decrementAndGet();
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ACTIVE_SESSIONS.incrementAndGet();
    }
}
