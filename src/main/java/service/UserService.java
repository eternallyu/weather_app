package service;

import lombok.RequiredArgsConstructor;
import model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
