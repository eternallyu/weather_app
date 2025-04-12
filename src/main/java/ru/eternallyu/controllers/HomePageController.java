package ru.eternallyu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.eternallyu.dto.LocationDto;
import ru.eternallyu.dto.RegistrationUserDto;
import ru.eternallyu.dto.weather.WeatherDto;
import ru.eternallyu.model.entity.Session;
import ru.eternallyu.service.LocationService;
import ru.eternallyu.service.SessionService;
import ru.eternallyu.service.UserService;
import ru.eternallyu.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HomePageController {

    private final SessionService sessionService;

    private final UserService userService;

    private final LocationService locationService;

    private final SessionUtil sessionUtil;

    @GetMapping("/home")
    public String homePage(@CookieValue(value = "session", defaultValue = "") String sessionFromCookie, Model model) {

        if (sessionFromCookie.isEmpty()) {
            addEmptyAttributes(model);
            return "index";
        }

        Session session = sessionService.getSession(UUID.fromString(sessionFromCookie));

        if (sessionUtil.isInvalidSession(session)) {
            addEmptyAttributes(model);
            return "index";
        }

        addNonEmptyAttributes(model, session);

        return "index";
    }

    private void addNonEmptyAttributes(Model model, Session session) {
        RegistrationUserDto registrationUserDto = userService.getUserDto(session.getUser().getLogin());
        List<LocationDto> locationDtoList = locationService.getAllUserLocations(registrationUserDto.getLogin());
        List<WeatherDto> weatherDtoList = locationService.getWeatherForUserLocations(locationDtoList);

        model.addAttribute("user", registrationUserDto);
        model.addAttribute("locationDtoList", locationDtoList);
        model.addAttribute("weatherDtoList", weatherDtoList);
    }

    private static void addEmptyAttributes(Model model) {
        model.addAttribute("user", null);
        model.addAttribute("locationDtoList", new ArrayList<>());
        model.addAttribute("weatherDtoList", new ArrayList<>());
    }
}
