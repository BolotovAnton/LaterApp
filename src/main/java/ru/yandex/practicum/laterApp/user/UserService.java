package ru.yandex.practicum.laterApp.user;

import ru.yandex.practicum.laterApp.user.dto.UserDto;

import java.util.List;

interface UserService {
    List<UserDto> getAllUsers();
    UserDto saveUser(UserDto userDto);
}