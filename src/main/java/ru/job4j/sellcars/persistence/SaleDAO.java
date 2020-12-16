package ru.job4j.sellcars.persistence;

import ru.job4j.sellcars.domain.Brand;
import ru.job4j.sellcars.domain.Car;
import ru.job4j.sellcars.domain.Model;
import ru.job4j.sellcars.domain.User;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SaleDAO {
    List<Brand> getBrands();

    List<Model> getModels(int brand);

    void add(int brandId, int modelId, String town, Calendar calendar, String photo, User user, double parseDouble);

    List<Car> getCars(Map<String, String[]> param);

    <T> void create(final T model);

    Optional<User> findByEmail(String email);

    Optional<Car> getCarById(long id);

    void soldCar(long id);
}
