package dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record LocationDto(String name, Integer userId, BigDecimal latitude, BigDecimal longitude) {
}
