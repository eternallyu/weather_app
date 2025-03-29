package ru.eternallyu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.eternallyu.dto.LocationDto;
import ru.eternallyu.model.entity.Session;
import ru.eternallyu.model.entity.User;
import ru.eternallyu.service.LocationService;
import ru.eternallyu.service.SessionService;
import ru.eternallyu.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HomePageController {

    private final SessionService sessionService;

    private final UserService userService;

    private final LocationService locationService;

    @GetMapping("/home")
    public String homePage(@CookieValue(value = "session", defaultValue = "") String sessionFromCookie, Model model) {

        if (sessionFromCookie.isEmpty()) {
            return "index";
        }

        Session session = sessionService.getSession(UUID.fromString(sessionFromCookie));
        User user = userService.getUser(session.getUser().getLogin());
        List<LocationDto> locationDtoList = locationService.getAllUserLocations(user.getId());
        System.out.println(locationDtoList.size());
        model.addAttribute("user", user);
        model.addAttribute("locationDtoList", locationDtoList);
        return "index";
    }
}
