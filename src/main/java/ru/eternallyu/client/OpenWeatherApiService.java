package ru.eternallyu.client;

import org.springframework.stereotype.Service;
import ru.eternallyu.dto.SearchLocationDto;
import ru.eternallyu.dto.WeatherDto;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OpenWeatherApiService {
    public List<SearchLocationDto> findLocationsByName(String name) {
        return null;
    }

    public WeatherDto findWeatherByCoordinates(BigDecimal latitude, BigDecimal longitude) {
        return null;
    }
}
