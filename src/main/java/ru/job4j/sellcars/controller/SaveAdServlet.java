package ru.job4j.sellcars.controller;

import ru.job4j.sellcars.domain.User;
import ru.job4j.sellcars.persistence.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

@WebServlet("/save.do")
public class SaveAdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String town = req.getParameter("town");
        String price = req.getParameter("price");
        String photo = req.getParameter("photo");
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, Integer.parseInt(req.getParameter("release_year")));
        Store.insOf().add(Integer.parseInt(brand), Integer.parseInt(model), town, calendar, photo, user, Double.parseDouble(price));
        resp.sendRedirect(req.getContextPath());
    }
}
