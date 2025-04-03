package ru.eternallyu.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {

    @JsonProperty("weather")
    private List<WeatherCondition> weather;

    @JsonProperty("main")
    private TemperatureInfo temp;

    @JsonProperty("sys")
    private SysWeatherInfo sys;

    @JsonProperty("name")
    private String name;
}
