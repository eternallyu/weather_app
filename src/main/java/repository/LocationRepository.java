package repository;

import model.entity.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository {
    List<Location> findByUserId(Integer userId);
}
