package ru.eternallyu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.eternallyu.dto.RegistrationUserDto;
import ru.eternallyu.mapper.UserMapper;
import ru.eternallyu.model.entity.User;
import ru.eternallyu.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public RegistrationUserDto getUserDto(String login) {
        return userRepository.findByLogin(login).map(userMapper::mapUserToUserDto).orElse(null);
    }

    public void createUser(RegistrationUserDto registrationUserDto) {

        User user = userMapper.mapUserDtoToUser(registrationUserDto);

        userRepository.save(user);
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }
}
