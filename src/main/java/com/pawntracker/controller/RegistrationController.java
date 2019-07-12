package com.pawntracker.controller;

import com.pawntracker.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/signup")
    public String signUpPage( Model model ) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }
}
