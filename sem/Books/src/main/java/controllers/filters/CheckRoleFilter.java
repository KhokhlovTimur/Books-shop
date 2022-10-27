package controllers.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.users.UsersService;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CheckRoleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String role = (String) req.getSession().getAttribute("role");
        User user = (User) req.getSession().getAttribute("user");
        if (role == null || user == null) {
            req.getSession().setAttribute("role", "noAuth");
        } else if (user != null && role != null) {
            req.getSession().setAttribute("role", ((UsersService) getServletContext().getAttribute("usersService")).findUserById(user.getId()).get().getRole());
        }
        chain.doFilter(req, res);
    }
}
