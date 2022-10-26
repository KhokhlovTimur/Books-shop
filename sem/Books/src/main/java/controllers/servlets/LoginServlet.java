package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import services.users.UsersService;
import services.utils.HashConverter;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersService usersService = (UsersService) getServletContext().getAttribute("usersService");
        String button = req.getParameter("button");
        if (button != null && button.equals("exit")) {
            req.getSession().setAttribute("role", null);
            req.getSession().setAttribute("role", "noAuth");
            req.setAttribute("button", null);
        }
        HttpSession session = req.getSession();
        if (nonNull(button)) {
            switch (button) {
                case "reg":
                    String passwordReg = req.getParameter("passwordReg");
                    String loginReg = req.getParameter("loginReg");
                    String passwordReg2 = req.getParameter("passwordReg2");
                    if (nonNull(passwordReg) && nonNull(loginReg) && nonNull(passwordReg2)) {
                        if (loginReg.length() > 0 && passwordReg.length() > 0 && passwordReg.equals(passwordReg2)) {
                            session.setAttribute("login", loginReg);
                            req.setAttribute("button", null);
                            if (!usersService.findUserByLoginAndPassw(loginReg, HashConverter.hashPassword(passwordReg)).isPresent() && !usersService.findUserByLogin(loginReg).isPresent()) {
                                usersService.saveUser(User.builder()
                                        .login(loginReg)
                                        .password(HashConverter.hashPassword(passwordReg))
                                        .role("auth").build());
                            }
                        }
                        resp.sendRedirect("/login");
                    }
                    break;
                case "log":
                    String passwordLog = req.getParameter("passwordLog");
                    String loginLog = req.getParameter("loginLog");
                    if (nonNull(passwordLog) && nonNull(loginLog)) {
                        if (passwordLog.length() > 0 && loginLog.length() > 0) {
                            session.setAttribute("login", loginLog);
                            session.setAttribute("password", passwordLog);
                            req.setAttribute("button", null);
                            if (usersService.findUserByLoginAndPassw(loginLog, HashConverter.hashPassword(passwordLog)).isPresent()) {
                                User user = usersService.findUserByLoginAndPassw(loginLog, HashConverter.hashPassword(passwordLog)).get();
                                session.setAttribute("role", "auth");
                                session.setAttribute("userId", user.getId());
                                session.setAttribute("user", usersService.findUserById(user.getId()).get());
                                if (usersService.findUserByLoginAndPassw(loginLog, HashConverter.hashPassword(passwordLog)).get().getRole().equals("admin")) {
                                    session.setAttribute("role", "admin");
                                }
                                resp.sendRedirect("/menu");
                            } else {
                                resp.sendRedirect("/login");
                            }
                        } else {
                            resp.sendRedirect("/login");
                        }
                    }
                    break;
                case "without":
                    session.setAttribute("role", "noAuth");
                    req.setAttribute("button", null);
                    resp.sendRedirect("/menu");
                    break;
            }
        }
    }

}
