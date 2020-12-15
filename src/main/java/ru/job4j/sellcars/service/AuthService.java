package ru.job4j.sellcars.service;

import ru.job4j.sellcars.domain.User;
import ru.job4j.sellcars.persistence.Store;

public class AuthService {
    public void newUserService(String login, String email, String password) {
        User user = User.of(login, email, password);
        Store.insOf().create(user);
    }
}
