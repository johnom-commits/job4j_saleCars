package ru.job4j.sellcars.controller;

import ru.job4j.sellcars.domain.User;
import ru.job4j.sellcars.service.TextHTML;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/view.do")
public class ViewCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(req.getParameter("id"));
        var writer = new PrintWriter(resp.getOutputStream(), true);
        User user = (User) req.getSession().getAttribute("user");
        writer.println(TextHTML.htmlCar(id, user));
    }
}
