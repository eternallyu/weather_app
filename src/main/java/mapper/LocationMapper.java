package mapper;

import dto.LocationDto;
import model.entity.Location;
import org.springframework.stereotype.Component;

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
