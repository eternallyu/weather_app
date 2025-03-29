package service;

import lombok.RequiredArgsConstructor;
import model.entity.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.SessionRepository;

import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public Session getSession(UUID uuid) {
        return sessionRepository.findById(uuid).orElse(null);
    }
}
