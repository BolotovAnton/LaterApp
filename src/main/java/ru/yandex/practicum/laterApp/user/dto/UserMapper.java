package ru.yandex.practicum.laterApp.user.dto;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.laterApp.user.UserState;
import ru.yandex.practicum.laterApp.user.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm:ss");

    public static UserDto mapToUserDto(User user) {
        Integer id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String registrationDate = user.getRegistrationDate().toString();
        UserState userState = user.getState();
        LocalDate dateOfBirth = user.getDateOfBirth();
        return new UserDto(id, firstName, lastName, email, registrationDate, userState, dateOfBirth);
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User();
        LocalDateTime registrationDate = LocalDateTime.parse(userDto.getRegistrationDate(), formatter);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRegistrationDate(registrationDate);
        user.setState(userDto.getState());
        return user;
    }

    public static List<UserDto> mapToUserDto(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(UserMapper.mapToUserDto(user));
        }
        return userDtoList;
    }
}
