package ru.eternallyu.mapper;

import org.springframework.stereotype.Component;
import ru.eternallyu.dto.LocationDto;
import ru.eternallyu.model.entity.Location;

@Component
public class LocationMapper {
    public LocationDto mapLocationToDto(Location location) {
        return buildLocationDto(location);
    }

    private static LocationDto buildLocationDto(Location location) {
        return LocationDto.builder()
                .name(location.getName())
                .userId(location.getUser().getId())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
