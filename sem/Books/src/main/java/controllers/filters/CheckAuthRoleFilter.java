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

@WebFilter(urlPatterns = {"/profile/*", "/cart/*"})
public class CheckAuthRoleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            String role = ((UsersService) getServletContext().getAttribute("usersService")).findUserById(user.getId()).get().getRole();
            if (role != null && (role.equals("auth") || role.equals("admin"))){
                chain.doFilter(req, res);
            }
            else {
                req.getSession().setAttribute("role", "noAuth");
                res.sendRedirect("/menu");
            }
        }
        else {
            req.getSession().setAttribute("role", "noAuth");
            res.sendRedirect("/menu");
        }
    }
}
