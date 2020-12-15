package ru.job4j.sellcars.controller;

import ru.job4j.sellcars.persistence.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sold.do")
public class SoldServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String sell = req.getParameter("sell");
        if (sell != null) {
            long id = Long.parseLong(sell);
            Store.insOf().soldCar(id);
        }
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
