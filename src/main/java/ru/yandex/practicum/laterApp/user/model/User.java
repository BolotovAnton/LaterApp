package ru.yandex.practicum.laterApp.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.laterApp.item.model.Item;
import ru.yandex.practicum.laterApp.user.UserState;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Enumerated(EnumType.STRING)
    private UserState state;

    @OneToMany(mappedBy = "user")
    private Set<Item> items;

    private LocalDate dateOfBirth;
}
