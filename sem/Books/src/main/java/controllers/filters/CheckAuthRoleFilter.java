package controllers.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/profile/*", "/cart/*"})
public class CheckAuthRoleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String role = (String) req.getSession().getAttribute("role");
        if (role != null && !role.equals("auth") && !role.equals("admin")) {
            res.sendRedirect("/menu");
        }
      else {
          chain.doFilter(req, res);
        }
    }
}