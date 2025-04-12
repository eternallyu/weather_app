package ru.eternallyu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eternallyu.dto.LocationDto;
import ru.eternallyu.dto.SearchLocationDto;
import ru.eternallyu.dto.weather.WeatherDto;
import ru.eternallyu.mapper.LocationMapper;
import ru.eternallyu.model.entity.Location;
import ru.eternallyu.repository.LocationRepository;
import ru.eternallyu.util.OpenWeatherApiClient;

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

    public void createLocation(LocationDto locationDto) {
        Location location = locationMapper.mapDtoToLocation(locationDto);

        locationRepository.save(location);
    }

    public List<WeatherDto> getWeatherForUserLocations(List<LocationDto> locationDtoList) {
        return locationDtoList.stream()
                .map(locationDto -> openWeatherApiClient.getWeatherByCoordinates(
                        locationDto.getLatitude(),
                        locationDto.getLongitude()
                ))
                .toList();
    }
}
