package ru.yandex.practicum.laterApp.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.laterApp.user.dto.UserDto;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("users have been found");
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto saveNewUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
}