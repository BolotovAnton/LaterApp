package ru.yandex.practicum.laterApp.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.yandex.practicum.laterApp.user.dto.UserDto;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class UserDtoJsonTest {

    @Autowired
    private JacksonTester<UserDto> json;

    @Test
    void testUserDto() throws Exception {
        UserDto userDto = new UserDto(
                1,
                "John",
                "Doe",
                "john.doe@mail.com",
                "2022.07.03 19:55:00",
                UserState.ACTIVE,
                LocalDate.of(1986, 7, 3));

        JsonContent<UserDto> result = json.write(userDto);

        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.firstName").isEqualTo("John");
        assertThat(result).extractingJsonPathStringValue("$.lastName").isEqualTo("Doe");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("john.doe@mail.com");
        assertThat(result).extractingJsonPathStringValue("$.dateOfBirth").isEqualTo("03-07-1986");
    }
}