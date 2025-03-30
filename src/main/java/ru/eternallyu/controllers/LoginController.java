package ru.eternallyu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.eternallyu.dto.LoginUserDto;
import ru.eternallyu.dto.RegistrationUserDto;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new LoginUserDto());
        return "sign-in";
    }

    @GetMapping("/logout")
    public String logout() {
        //logout
        return "index";
    }
}
