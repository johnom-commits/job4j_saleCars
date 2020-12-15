package ru.job4j.sellcars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, length = 25)
    private String login;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, unique = true, length = 25)
    private String password;

    public static User of(String login, String email, String password) {
        User user = new User();
        user.login = login;
        user.email = email;
        user.password = password;
        return user;
    }
}
