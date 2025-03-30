package ru.eternallyu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.eternallyu.dto.LoginUserDto;
import ru.eternallyu.exception.UserAlreadyLoggedInException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SessionService sessionService;

    private final UserService userService;

    public UUID loginUser(LoginUserDto user) {

        int userId = getUserIdFromUserDto(user);

        if (sessionService.userHasActiveSession(userId)) {
            throw new UserAlreadyLoggedInException("User is already logged in."); //catch
        }

        sessionService.createSession(userId);

        return sessionService.getSessionByUserId(userId).getId();
    }

    private int getUserIdFromUserDto(LoginUserDto user) {
        return userService.getUserByLogin(user.getLogin()).getId();
    }
}
