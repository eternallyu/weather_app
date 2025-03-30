package ru.eternallyu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.eternallyu.dto.UserDto;
import ru.eternallyu.exception.UserAlreadyExists;
import ru.eternallyu.mapper.UserMapper;
import ru.eternallyu.model.entity.User;
import ru.eternallyu.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto getUserDto(String login) {
        return userRepository.findByLogin(login).map(userMapper::mapUserToUserDto).orElse(null);
    }

    public void createUser(UserDto userDto) {

        User user = userMapper.mapUserDtoToUser(userDto);

        userRepository.save(user);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }
}
