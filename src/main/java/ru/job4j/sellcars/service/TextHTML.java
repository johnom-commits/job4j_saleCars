package ru.job4j.sellcars.service;

import org.json.simple.JSONObject;
import ru.job4j.sellcars.domain.Brand;
import ru.job4j.sellcars.domain.Car;
import ru.job4j.sellcars.domain.Model;
import ru.job4j.sellcars.domain.User;
import ru.job4j.sellcars.persistence.Store;

import java.text.SimpleDateFormat;
import java.util.*;

public class TextHTML {

    public static String brands() {
        var select = new StringBuilder("<option value=-1 selected>Choose brand</option>");
        for (Brand b : Store.insOf().getBrands()) {
            select.append("<option value=")
                    .append(b.getId())
                    .append(">")
                    .append(b.getName())
                    .append("</option>");
        }
        return select.toString();
    }

    public static String models(int brand) {
        var select = new StringBuilder("<option selected>Choose your car model</option>");
        for (Model m : Store.insOf().getModels(brand)) {
            select.append("<option value=")
                    .append(m.getId())
                    .append(">")
                    .append(m.getName())
                    .append("</option>");
        }
        return select.toString();
    }

    public static String ads(User user, Map<String, String[]> param) {
        Map<String, String> map = new HashMap<>();
        map.put("ads", htmlAds(Store.insOf().getCars(param)));
        map.put("login", htmlLogin(user));
        map.put("brands", brands());
        return new JSONObject(map).toJSONString();
    }

    private static String htmlAds(List<Car> cars) {
        var dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        var html = new StringBuilder();
        for (Car car : cars) {
            html.append("<div class=\"col-md-4\">\n" +
                    "<div class=\"card mb-4 shadow-sm\">\n" +
                    "<img class=\"img-fluid\" src=\"photo.do?name=")
                    .append(car.getPhoto())
                    .append("\" class=\"bd-placeholder-img card-img-top img-fluid\"/><div class=\"card-body\"><a href=\"view.html?id=")
                    .append(car.getId())
                    .append("\" class=\"card-link\">")
                    .append(car.getBrand().getName())
                    .append(" ")
                    .append(car.getModel().getName())
                    .append(", ")
                    .append(car.getReleaseYear().get(Calendar.YEAR))
                    .append("</a><div class=\"card-text\"><p>")
                    .append(String.format("%.0f", car.getPrice()))
                    .append(" RUB")
                    .append("<br>")
                    .append(car.getTown())
                    .append("</p></div></div><div class=\"card-footer\">")
                    .append(dateFormat.format(car.getCreated()))
                    .append("</div></div></div>");
        }
        return html.toString();
    }

    private static String htmlLogin(User user) {
        String login;
        if (user == null) {
            login = "<li class=\"nav-item\"><a class=\"nav-link\" href=\"login.html\">Log in</a></li>\n" +
                    "<li class=\"nav-item\"><a class=\"nav-link\" href=\"reg.html\">Authorize</a></li>";
        } else {
            login = "<li class=\"nav-item\"><a class=\"nav-link\" href=\"login.html\">" + user.getLogin() + " | Log out</a></li>";
        }
        return login;
    }

    public static String htmlCar(long id, User user) {
        Optional<Car> optionalCar = Store.insOf().getCarById(id);
        if (optionalCar.isEmpty()) {
            return "";
        }
        Car car = optionalCar.get();
        var html = new StringBuilder("<h1>")
                .append(car.getBrand().getName())
                .append(" ")
                .append(car.getModel().getName())
                .append("</h1><ul class=\"list-group\">\n")
                .append("<li class=\"list-group-item\">Price: ")
                .append(String.format("%.0f", car.getPrice()))
                .append(" RUB</li>\n")
                .append("<li class=\"list-group-item\">Year of release: ")
                .append(car.getReleaseYear().get(Calendar.YEAR))
                .append("</li>\n")
                .append("<li class=\"list-group-item\">Town: ")
                .append(car.getTown())
                .append("</li>\n")
                .append("<li class=\"list-group-item\">Seller: ")
                .append(car.getUser().getLogin())
                .append("</li>\n");
        if (car.getUser().getLogin().equals(user.getLogin())) {
            html.append("<form action=\"sold.do\" method=\"post\"><p></p><div class=\"form-check\">")
                    .append("<input class=\"form-check-input\" type=\"checkbox\" name=\"sell\" value=") //\"sold\">")
                    .append(id)
                    .append(">")
                    .append("<label class=\"form-check-label\" for=\"defaultCheck1\">Sold</label></div>")
                    .append("<p></p><button type=\"submit\" class=\"btn btn-primary\" id=\"save\">Save</button></form>");
        }
        return html.toString();
    }
}
