package ru.eternallyu.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.eternallyu.exception.UserAuthorizationException;
import ru.eternallyu.model.entity.Session;
import ru.eternallyu.service.SessionService;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionUtil {

    public static final int SESSION_DURATION_SECONDS = 7200;

    public boolean isInvalidSession(Session session) {
        return session == null || session.getId() == null || isExpiredSession(session);
    }

    private boolean isExpiredSession(Session session) {
        return LocalDateTime.now().isAfter(session.getExpiresAt());
    }

    public LocalDateTime getSessionExpirationTime() {
        return LocalDateTime.now().plusSeconds(SESSION_DURATION_SECONDS);
    }
}
