package ru.yandex.practicum.laterApp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.laterApp.user.dto.UserDto;
import ru.yandex.practicum.laterApp.user.dto.UserMapper;
import ru.yandex.practicum.laterApp.user.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.mapToUserDto(userRepository.findAll());
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        userDto.setRegistrationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm:ss")));
        userDto.setState(UserState.ACTIVE);
        User user = UserMapper.mapToUser(userDto);
        userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }
}