package service;

import dto.LocationDto;
import lombok.RequiredArgsConstructor;
import mapper.LocationMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    public List<LocationDto> getAllUserLocations(int id) {
        return locationRepository.findByUserId(id).stream().map(locationMapper::mapLocationToDto).collect(Collectors.toList());
    }
}
