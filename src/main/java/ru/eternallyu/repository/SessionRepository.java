package ru.eternallyu.repository;

import ru.eternallyu.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    @NonNull
    Optional<Session> findById(@NonNull UUID id);

    Optional<Session> findByUserId(Integer sessionId);
}
