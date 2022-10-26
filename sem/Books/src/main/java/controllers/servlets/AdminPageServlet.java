package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import models.User;
import services.books.BooksService;
import services.users.UsersService;

import java.io.IOException;
import java.util.Arrays;

import static java.util.Objects.nonNull;

@WebServlet("/admin")
public class AdminPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] roles = req.getParameterValues("chooseRole");
        UsersService usersService = (UsersService) getServletContext().getAttribute("usersService");
        if (roles != null) {
            for (String role : roles) {
                if (!role.equals("none")) {
                    StringBuilder id = new StringBuilder();
                    int index = 0;
                    for(int i = 0; i < role.length(); i++){
                        if(Character.isDigit(role.charAt(i))){
                            id.append(role.charAt(i));
                            index++;
                        }
                    }
                    String newRole = role.substring(index);
                    User user = usersService.findUserById(Long.valueOf(id.toString())).get();
                    usersService.updateUser(User.builder()
                            .id(Long.valueOf(id.toString()))
                            .sessionId(user.getSessionId())
                            .login(user.getLogin())
                            .password(user.getPassword())
                            .role(newRole).build());
                }
            }
        }

        resp.sendRedirect("/admin");
    }
}
