package ru.eternallyu.service;

import lombok.RequiredArgsConstructor;
import ru.eternallyu.model.entity.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.eternallyu.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public Session getSession(UUID uuid) {
        return sessionRepository.findById(uuid).orElse(null);
    }

    public boolean isInvalidSession(Session session) {
        return session == null || session.getId() == null || isExpiredSession(session);
    }

    private boolean isExpiredSession(Session session) {
        return !LocalDateTime.now().isAfter(session.getExpiresAt());
    }
}
