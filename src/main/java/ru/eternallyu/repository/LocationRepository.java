package ru.eternallyu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eternallyu.model.entity.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByUserId(Integer userId);
}
