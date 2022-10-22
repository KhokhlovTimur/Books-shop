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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.util.Objects.nonNull;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        usersService = (UsersService) getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String button = req.getParameter("button");
        if (button != null && button.equals("exit")) {
            req.getSession().setAttribute("role", null);
            req.getSession().setAttribute("role", "noAuth");
            req.setAttribute("button", null);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String button = req.getParameter("button");
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
                                        .sessionId(session.getId())
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
                                User lastUser = usersService.findUserByLoginAndPassw(loginLog, HashConverter.hashPassword(passwordLog)).get();
                                session.setAttribute("role", "auth");
                                usersService.updateUser(User.builder().
                                        id(lastUser.getId()).
                                        role(lastUser.getRole())
                                        .sessionId(session.getId())
                                        .login(lastUser.getLogin())
                                        .password(lastUser.getPassword()).build());
                                session.setAttribute("userId", lastUser.getId());
                                session.setAttribute("user", usersService.findUserById(lastUser.getId()).get());
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
                    if (!usersService.findUserBySessionId(session.getId()).isPresent()) {
                        usersService.saveUser(User.builder().sessionId(session.getId()).role("noAuth").build());
                    }
                    session.setAttribute("role", "noAuth");
                    req.setAttribute("button", null);
                    resp.sendRedirect("/menu");
                    break;
            }
        }
    }

}
