package controllers.Listeners;

import dao.authors.impl.AuthorsRepositoryImpl;
import dao.books.impl.BooksRepositoryImpl;
import dao.users.impl.UsersRepositoryImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import services.MapService;
import services.authors.AuthorsServiceImpl;
import services.books.BooksServiceImpl;
import services.users.UsersServiceImpl;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("usersService",new UsersServiceImpl(new UsersRepositoryImpl()));
        context.setAttribute("booksService",new BooksServiceImpl(new BooksRepositoryImpl()));
        context.setAttribute("authorsService", new AuthorsServiceImpl(new AuthorsRepositoryImpl()));
        context.setAttribute("mapService", new MapService(new BooksRepositoryImpl(), new AuthorsRepositoryImpl()));
    }
}
