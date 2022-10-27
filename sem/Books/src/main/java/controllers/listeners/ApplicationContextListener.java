package controllers.listeners;

import dao.booksDao.BooksRepository;
import dao.orderBookDao.impl.OrderBookRepositoryImpl;
import dao.authorsDao.impl.AuthorsRepositoryImpl;
import dao.booksDao.impl.BooksRepositoryImpl;
import dao.cartsDao.impl.CartRepositoryImpl;
import dao.ordersDao.impl.OrderRepositoryImpl;
import dao.usersDao.impl.UsersRepositoryImpl;
import dao.utils.SearchDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import providers.MyDriverManager;
import services.utils.*;
import services.authors.impl.AuthorsServiceImpl;
import services.books.impl.BooksServiceImpl;
import services.carts.impl.CartServiceImpl;
import services.orderBookService.impl.OrderBookServiceImpl;
import services.orderService.impl.OrderServiceImpl;
import services.users.impl.UsersServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    private final Connection connection = MyDriverManager.getConnection();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        executeUpdateStatement(SQL_CREATE_TABLE_USERS);
        executeUpdateStatement(SQL_CREATE_TABLE_AUTHORS);
        executeUpdateStatement(SQL_CREATE_TABLE_BOOKS);
        executeUpdateStatement(SQL_CREATE_TABLE_CART);
        executeUpdateStatement(SQL_CREATE_TABLE_ORDER);
        executeUpdateStatement(SQL_CREATE_TABLE_ORDER_BOOK);
        executeUpdateStatement(SQL_INSERT_ADMIN);
        executeUpdateStatement(SQL_INSERT_VALUES_TO_AUTHORS);
        BooksRepository booksRepository = new BooksRepositoryImpl();
        MapService mapService = new MapService(booksRepository, new AuthorsRepositoryImpl(), new CartRepositoryImpl(), new OrderBookRepositoryImpl(), new OrderRepositoryImpl());
        context.setAttribute("usersService",new UsersServiceImpl(new UsersRepositoryImpl()));
        context.setAttribute("booksService",new BooksServiceImpl(booksRepository));
        context.setAttribute("authorsService", new AuthorsServiceImpl(new AuthorsRepositoryImpl()));
        context.setAttribute("mapService", mapService);
        context.setAttribute("searchService", new SearchBooksService(new BooksServiceImpl(booksRepository), new AuthorsServiceImpl(new AuthorsRepositoryImpl()), new SearchDao()));
        context.setAttribute("sortService", new SortService(mapService));
        context.setAttribute("cartService", new CartServiceImpl(new CartRepositoryImpl()));
        context.setAttribute("orderService", new OrderServiceImpl(new OrderRepositoryImpl()));
        context.setAttribute("orderBookService", new OrderBookServiceImpl(new OrderBookRepositoryImpl()));
        context.setAttribute("cartSumService", new CartSumService(new CartRepositoryImpl(), booksRepository));
    }

    private void executeUpdateStatement(String query){
        try( PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String SQL_CREATE_TABLE_USERS = "create table if not exists users\n" +
            "(\n" +
            "    id         bigserial primary key,\n" +
            "    login      varchar(30),\n" +
            "    password   varchar(50),\n" +
            "    role       varchar(15),\n" +
            "    email varchar(40) default 'Не указан',\n" +
            "    balance integer\n" +
            ");";
    private static final String SQL_INSERT_ADMIN = "insert into users(login, password, role) VALUES\n" +
            "    ('admin', '" + HashConverter.hashPassword("admin") + "','admin')";

    private static final String SQL_CREATE_TABLE_BOOKS = "create table if not exists books\n" +
            "(\n" +
            "    id                  bigserial primary key,\n" +
            "    title               varchar(50),\n" +
            "    author_id           bigint references authors (id),\n" +
            "    year_of_publication integer,\n" +
            "    price               integer,\n" +
            "    description varchar\n" +
            ");";

    private static final String SQL_CREATE_TABLE_CART = "create table if not exists cart(\n" +
            "    id bigint,\n" +
            "    user_id bigint references users(id) on delete cascade,\n" +
            "    book_id bigint references books(id) on  delete cascade\n" +
            ")";

    private static final String SQL_CREATE_TABLE_ORDER = "create table if not exists orders(\n" +
            "    id bigserial primary key,\n" +
            "    price bigint,\n" +
            "    user_id bigint references users(id) on delete no action\n" +
            ")";

    private static final String SQL_CREATE_TABLE_ORDER_BOOK = "create table if not exists order_book(\n" +
            "    book_id bigint references books(id) on delete cascade,\n" +
            "    order_id bigint references orders(id) on delete cascade\n" +
            ")";

    private static final String SQL_CREATE_TABLE_AUTHORS = "create table if not exists authors\n" +
            "(\n" +
            "    id         bigserial   primary key,\n" +
            "    name       varchar(20),\n" +
            "    surname    varchar(30),\n" +
            "    birth_year integer\n" +
            ")\n";

    private static final String SQL_INSERT_VALUES_TO_AUTHORS = "insert into authors (name, surname, birth_year)\n" +
            "values ('Стивен', 'Кинг', 1947),\n" +
            "       ('Дэн', 'Браун', 1964)";

}
