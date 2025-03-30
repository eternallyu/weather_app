package ru.eternallyu.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.eternallyu.dto.UserDto;
import ru.eternallyu.service.UserService;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @GetMapping("/registration")
    public String signUp(Model model) {
        model.addAttribute("user", buildEmptyUserDto());
        return "sign-up";
    }

    @PostMapping("/registration")
    public String signUp(@ModelAttribute("user") @Valid UserDto userDto,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "sign-up";
        }

        if (userService.existsByLogin(userDto.getLogin())) {
            bindingResult.rejectValue("login", "error.user", "User already exists.");
            return "sign-up";
        }

        userService.createUser(userDto);

        return "redirect:/login";
    }

    private static UserDto buildEmptyUserDto() {
        return UserDto.builder().build();
    }
}
