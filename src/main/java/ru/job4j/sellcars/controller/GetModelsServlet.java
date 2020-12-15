package ru.job4j.sellcars.controller;

import ru.job4j.sellcars.service.TextHTML;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/models.do")
public class GetModelsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        var brand = req.getParameter("brand_id");
        var writer = new PrintWriter(resp.getOutputStream(), true);
        writer.println(TextHTML.models(Integer.parseInt(brand)));
    }
}
