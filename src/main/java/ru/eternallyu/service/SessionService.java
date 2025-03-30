package ru.eternallyu.service;

import jakarta.persistence.metamodel.IdentifiableType;
import lombok.RequiredArgsConstructor;
import ru.eternallyu.model.entity.Session;
import org.springframework.stereotype.Service;
import ru.eternallyu.model.entity.User;
import ru.eternallyu.repository.SessionRepository;
import ru.eternallyu.util.SessionUtil;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    private final SessionUtil sessionUtil;

    private final UserService userService;

    public Session getSession(UUID uuid) {
        return sessionRepository.findById(uuid).orElse(null);
    }

    public void createSession(Integer userId) {
        UUID uuid = UUID.randomUUID();
        User user = userService.getUserById(userId);
        LocalDateTime sessionExpirationTime = sessionUtil.getSessionExpirationTime();

        Session session = new Session(uuid, user, sessionExpirationTime);
        sessionRepository.save(session);
    }

    public boolean userHasActiveSession(Integer userId) {
        Session session = getSessionByUserId(userId);
        return !sessionUtil.isInvalidSession(session);
    }

    public Session getSessionByUserId(int userId) {
        return sessionRepository.findByUserId(userId).orElse(null);
    }
}
