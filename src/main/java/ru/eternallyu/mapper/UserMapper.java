package ru.eternallyu.mapper;

import org.springframework.stereotype.Component;
import ru.eternallyu.dto.RegistrationUserDto;
import ru.eternallyu.model.entity.User;

@Component
public class UserMapper {
    public RegistrationUserDto mapUserToUserDto(User user) {

        if (user == null) {
            return null;
        }

        return buildUserDto(user);
    }

    private static RegistrationUserDto buildUserDto(User user) {
        return RegistrationUserDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    public User mapUserDtoToUser(RegistrationUserDto registrationUserDto) {
        return new User(registrationUserDto.getLogin(), registrationUserDto.getPassword());
    }
}
