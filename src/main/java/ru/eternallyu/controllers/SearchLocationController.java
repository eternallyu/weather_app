package ru.eternallyu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eternallyu.dto.SearchLocationDto;
import ru.eternallyu.exception.InvalidLocationException;
import ru.eternallyu.service.LocationService;
import ru.eternallyu.util.LocationNameValidator;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/location-search")
public class SearchLocationController {

    private final LocationService locationService;

    @GetMapping
    public String searchLocation(@RequestParam(required = false, defaultValue = "") String name, Model model) {
        LocationNameValidator.validateLocationName(name);

        List<SearchLocationDto> locations = locationService.getLocationsByName(name);

        model.addAttribute("locations", locations);

        return "search-results";
    }

    @ExceptionHandler(InvalidLocationException.class)
    public String handleInvalidLocationException(InvalidLocationException exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }
}
