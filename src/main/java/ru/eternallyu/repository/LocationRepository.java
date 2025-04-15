package ru.eternallyu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eternallyu.model.entity.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByUserId(Integer userId);

    Optional<Location> findByUserIdAndName(Integer userId, String name);

    void deleteByUserIdAndName(Integer userId, String name);
}
