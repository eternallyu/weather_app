package ru.eternallyu.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDto {

    private int id;

    @NotEmpty(message = "Login should not be empty.")
    @Size(min = 2, max = 20, message = "Login should be between 2 and 20 characters.")
    private String login;

    @NotEmpty(message = "Password should not be empty.")
    @Size(min = 6, max = 30, message = "Password should be between 6 and 30 characters.")
    private String password;
}
