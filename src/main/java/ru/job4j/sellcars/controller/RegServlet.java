package ru.job4j.sellcars.controller;

import ru.job4j.sellcars.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var login = req.getParameter("login");
        var email = req.getParameter("email");
        var password = req.getParameter("password");
        new AuthService().newUserService(login, email, password);
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
