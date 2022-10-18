package controllers.listeners;

import dao.OrderBookDao.OrderBookImpl;
import dao.authorsDao.impl.AuthorsRepositoryImpl;
import dao.booksDao.impl.BooksRepositoryImpl;
import dao.cartsDao.impl.CartRepositoryImpl;
import dao.ordersDao.impl.OrderRepositoryImpl;
import dao.usersDao.impl.UsersRepositoryImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import services.MapService;
import services.authors.AuthorsServiceImpl;
import services.books.BooksServiceImpl;
import services.carts.CartServiceImpl;
import services.orderBookService.OrderBookServiceImpl;
import services.orderService.OrderServiceImpl;
import services.users.UsersServiceImpl;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("usersService",new UsersServiceImpl(new UsersRepositoryImpl()));
        context.setAttribute("booksService",new BooksServiceImpl(new BooksRepositoryImpl()));
        context.setAttribute("authorsService", new AuthorsServiceImpl(new AuthorsRepositoryImpl()));
        context.setAttribute("mapService", new MapService(new BooksRepositoryImpl(), new AuthorsRepositoryImpl(), new CartRepositoryImpl()));
        context.setAttribute("cartService", new CartServiceImpl(new CartRepositoryImpl()));
        context.setAttribute("orderService", new OrderServiceImpl(new OrderRepositoryImpl()));
        context.setAttribute("orderBookService", new OrderBookServiceImpl(new OrderBookImpl()));
    }
}
