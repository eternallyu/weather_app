package ru.eternallyu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eternallyu.dto.LocationDto;
import ru.eternallyu.dto.SearchLocationDto;
import ru.eternallyu.exception.InvalidLocationException;
import ru.eternallyu.exception.UserAuthorizationException;
import ru.eternallyu.exception.WeatherApiException;
import ru.eternallyu.model.entity.Session;
import ru.eternallyu.service.LocationService;
import ru.eternallyu.service.SessionService;
import ru.eternallyu.util.LocationNameValidator;
import ru.eternallyu.util.SessionUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SearchLocationController {

    private final LocationService locationService;

    private final SessionService sessionService;

    private final SessionUtil sessionUtil;

    @GetMapping("/search")
    public String searchLocation(@RequestParam("name") String name, Model model) {

        System.out.println("Search request for: " + name);
        
        LocationNameValidator.validateLocationName(name);

        List<SearchLocationDto> locations = locationService.getLocationsByName(name);

        model.addAttribute("locations", locations);

        return "search-results";
    }

    @PostMapping("/add")
    public String addLocation(@RequestParam("latitude") BigDecimal latitude,
                              @RequestParam("longitude") BigDecimal longitude,
                              @RequestParam("name") String name,
                              @CookieValue(value = "session", defaultValue = "") String sessionFromCookie) {

        if (sessionFromCookie.isEmpty()) {
            throw new UserAuthorizationException("User is not logged in");
        }

        Session session = sessionService.getSession(UUID.fromString(sessionFromCookie));

        if (sessionUtil.isInvalidSession(session)) {
            throw new UserAuthorizationException("User is not logged in");
        }

        int userId = session.getUser().getId();

        LocationDto locationDto = buildLocationDto(latitude, longitude, name, userId);

        locationService.createLocation(locationDto);

        return "redirect:/home";
    }

    private static LocationDto buildLocationDto(BigDecimal latitude, BigDecimal lon, String name, int userId) {
        return LocationDto.builder()
                .latitude(latitude)
                .longitude(lon)
                .name(name)
                .userId(userId)
                .build();
    }

    @ExceptionHandler(InvalidLocationException.class)
    public String handleInvalidLocationException(InvalidLocationException exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(WeatherApiException.class)
    public String handleWeatherApiException(WeatherApiException exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(UserAuthorizationException.class)
    public String handleUserAuthorizationException(UserAuthorizationException exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }
}
