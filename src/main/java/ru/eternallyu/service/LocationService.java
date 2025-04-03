package ru.eternallyu.service;

import org.springframework.transaction.annotation.Transactional;
import ru.eternallyu.util.OpenWeatherApiClient;
import ru.eternallyu.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import ru.eternallyu.dto.SearchLocationDto;
import ru.eternallyu.mapper.LocationMapper;
import org.springframework.stereotype.Service;
import ru.eternallyu.repository.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    private final UserService userService;

    private final OpenWeatherApiClient openWeatherApiClient;

    private final LocationMapper locationMapper;

    @Transactional
    public List<LocationDto> getAllUserLocations(String login) {
        int userId = userService.getUserByLogin(login).getId();
        return locationRepository.findByUserId(userId).stream().map(locationMapper::mapLocationToDto).collect(Collectors.toList());
    }

    public List<SearchLocationDto> getLocationsByName(String name) {
        return openWeatherApiClient.getLocationsByName(name);
    }
}
