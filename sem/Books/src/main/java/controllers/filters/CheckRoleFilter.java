package controllers.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CheckRoleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = ((User) req.getSession().getAttribute("user"));
        if(user == null || req.getSession().getAttribute("role") == null){
            req.getSession().setAttribute("role", "noAuth");
        }
        chain.doFilter(req, res);
    }
}
