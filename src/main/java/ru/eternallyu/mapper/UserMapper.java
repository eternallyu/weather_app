package ru.eternallyu.mapper;

import org.springframework.stereotype.Component;
import ru.eternallyu.dto.UserDto;
import ru.eternallyu.model.entity.User;

@Component
public class UserMapper {
    public UserDto mapUserToUserDto(User user) {

        if (user == null) {
            return null;
        }

        return buildUserDto(user);
    }

    private static UserDto buildUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    public User mapUserDtoToUser(UserDto userDto) {
        return new User(userDto.getLogin(), userDto.getPassword());
    }
}
