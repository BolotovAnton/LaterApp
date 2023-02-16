package ru.yandex.practicum.laterApp.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.laterApp.user.dto.UserDto;
import ru.yandex.practicum.laterApp.user.dto.UserMapper;
import ru.yandex.practicum.laterApp.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Transactional
@SpringBootTest(
        properties = "db.name=test",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserServiceImplTest {

    private final EntityManager em;
    private final UserService service;

    @Test
    void saveUser() {
        UserDto userDto = makeUserDto("some@email.com", "Пётр", "Иванов");
        service.saveUser(userDto);

        TypedQuery<User> query = em.createQuery("Select u from User u where u.email = :email", User.class);
        User user = query
                .setParameter("email", userDto.getEmail())
                .getSingleResult();

        assertThat(user.getId(), notNullValue());
        assertThat(user.getFirstName(), equalTo(userDto.getFirstName()));
        assertThat(user.getLastName(), equalTo(userDto.getLastName()));
        assertThat(user.getEmail(), equalTo(userDto.getEmail()));
        assertThat(user.getState(), equalTo(userDto.getState()));
        assertThat(user.getRegistrationDate(), notNullValue());
    }

    @Test
    @Rollback(value = false)
    void getAllUsers() {

        // given
        List<UserDto> sourceUsers = List.of(
                makeUserDto("ivan@email", "Ivan", "Ivanov"),
                makeUserDto("petr@email", "Petr", "Petrov"),
                makeUserDto("vasilii@email", "Vasilii", "Vasiliev")
        );

        for (UserDto user : sourceUsers) {
            User entity = UserMapper.mapToUser(user);
            em.persist(entity);
        }
        em.flush();

        // when
        List<UserDto> targetUsers = service.getAllUsers();

        // then
        assertThat(targetUsers, hasSize(sourceUsers.size()));
        for (UserDto sourceUser : sourceUsers) {
            assertThat(targetUsers, hasItem(allOf(
                    hasProperty("id", notNullValue()),
                    hasProperty("firstName", equalTo(sourceUser.getFirstName())),
                    hasProperty("lastName", equalTo(sourceUser.getLastName())),
                    hasProperty("email", equalTo(sourceUser.getEmail()))
            )));
        }
    }


    private UserDto makeUserDto(String email, String firstName, String lastName) {
        UserDto dto = new UserDto();
        dto.setEmail(email);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setRegistrationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm:ss")));
        dto.setState(UserState.ACTIVE);
        return dto;
    }
}