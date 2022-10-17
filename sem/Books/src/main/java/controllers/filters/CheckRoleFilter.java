package controllers.filters;

import dao.users.impl.UsersRepositoryImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.users.UsersService;
import services.users.UsersServiceImpl;

import java.io.IOException;

@WebFilter("/*")
public class CheckRoleFilter extends HttpFilter {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        usersService = new UsersServiceImpl(new UsersRepositoryImpl());
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        if(session.getAttribute("role") == null || session.getAttribute("role").equals("noAuth")){
            session.setAttribute("role", "noAuth");
        }
        chain.doFilter(req, res);
    }
}
