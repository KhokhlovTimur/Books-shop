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

@WebFilter("/admin/*")
public class CheckAdminFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        UsersService usersService = ((UsersService) getServletContext().getAttribute("usersService"));
        if (user != null && usersService.findUserById(user.getId()).get().getRole().equals("admin")) {
            user.setRole("admin");
            req.getSession().setAttribute("user", user);
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("/menu");
        }
    }
}
