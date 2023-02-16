package ru.yandex.practicum.laterApp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.laterApp.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
