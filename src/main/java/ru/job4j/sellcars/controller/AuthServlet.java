package ru.job4j.sellcars.controller;

import ru.job4j.sellcars.domain.User;
import ru.job4j.sellcars.persistence.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth.do")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var email = req.getParameter("email");
        var password = req.getParameter("password");
        Optional<User> user = Store.insOf().findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            var sc = req.getSession();
            sc.setAttribute("user", user.get());
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.html").forward(req, resp);
        }
    }
}
