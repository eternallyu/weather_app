package ru.eternallyu.service;

import ru.eternallyu.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import ru.eternallyu.mapper.LocationMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.eternallyu.repository.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    public List<LocationDto> getAllUserLocations(int id) {
        return locationRepository.findByUserId(id).stream().map(locationMapper::mapLocationToDto).collect(Collectors.toList());
    }
}
