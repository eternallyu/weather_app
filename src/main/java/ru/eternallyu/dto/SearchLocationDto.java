package ru.eternallyu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchLocationDto {
    private String name;
    private int latitude;
    private int longitude;
    private String country;
}
