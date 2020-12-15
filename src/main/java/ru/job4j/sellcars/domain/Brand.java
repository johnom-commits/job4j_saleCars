package ru.job4j.sellcars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    public static Brand of(String name) {
        Brand brand = new Brand();
        brand.name = name;
        return brand;
    }
}
