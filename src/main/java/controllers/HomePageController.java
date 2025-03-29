package controllers;

import dto.LocationDto;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import model.entity.Session;
import model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import service.LocationService;
import service.SessionService;
import service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HomePageController {

    private final SessionService sessionService;

    private final UserService userService;

    private final LocationService locationService;

    @GetMapping("/home")
    public String homePage(@CookieValue(value = "session") Cookie sessionFromCookie, Model model) {
        Session session = sessionService.getSession(UUID.fromString(String.valueOf(sessionFromCookie)));
        User user = userService.getUser(session.getUser().getId());
        List<LocationDto> locationDtoList = locationService.getAllUserLocations(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("locationDtoList", locationDtoList);
        return "index";
    }
}
