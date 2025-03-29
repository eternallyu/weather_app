package ru.eternallyu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        //login
        return "sign-in";
    }

    @GetMapping("/logout")
    public String logout() {
        //logout
        return "index";
    }
}
