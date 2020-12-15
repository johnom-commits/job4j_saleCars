package ru.job4j.sellcars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
    @Column(nullable = false)
    private String town;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar releaseYear;
    @Column(nullable = false)
    private double price;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
    @org.hibernate.annotations.CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private boolean sold;
    private String photo;

    public static Car of(Brand brand, Model model, String town, Calendar releaseYear, User user, double price) {
        Car car = new Car();
        car.brand = brand;
        car.model = model;
        car.town = town;
        car.releaseYear = releaseYear;
        car.price = price;
        car.user = user;
        return car;
    }
}
