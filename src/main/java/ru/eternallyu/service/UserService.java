package ru.eternallyu.service;

import lombok.RequiredArgsConstructor;
import ru.eternallyu.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.eternallyu.repository.UserRepository;

@Service
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }
}
