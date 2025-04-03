package ru.eternallyu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eternallyu.dto.SearchLocationDto;
import ru.eternallyu.dto.weather.WeatherDto;
import ru.eternallyu.exception.InvalidLocationException;
import ru.eternallyu.exception.WeatherApiException;
import ru.eternallyu.service.LocationService;
import ru.eternallyu.util.LocationNameValidator;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/location-search")
public class SearchLocationController {

    private final LocationService locationService;

    @GetMapping
    public String searchLocation(@RequestParam("name") String name, Model model) {
        LocationNameValidator.validateLocationName(name);

        List<SearchLocationDto> locations = locationService.getLocationsByName(name);

        model.addAttribute("locations", locations);

        return "search-results";
    }

    @PostMapping("/add")
    public String addLocation(@RequestParam("latitude") BigDecimal latitude,
                              @RequestParam("longitude") BigDecimal lon,
                              @RequestParam("name") String name,
                              Model model) {
        //???
        return "redirect:/home";
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
}
