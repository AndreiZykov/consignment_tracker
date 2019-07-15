package com.pawntracker.controller;

import com.pawntracker.entity.User;
import com.pawntracker.service.SecurityService;
import com.pawntracker.service.UserService;
import com.pawntracker.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;


    private SecurityService securityService;



    private UserValidator userValidator;


    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
       this.userService = userService;
       this.securityService = securityService;
       this.userValidator = userValidator;
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid  User user, BindingResult bindingResult) {
        userValidator.validate(user,bindingResult);
        final String rawPassword = user.getPassword();
        if (bindingResult.hasErrors()) {

            System.out.println("errors" + bindingResult.getAllErrors());
            return "registration";
        }

        userService.saveUserOrUpdate(user);
        System.out.println(user.getUsername()+ " " + rawPassword);
        securityService.autoLogin(user.getUsername(), rawPassword);


        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(  Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully. ");

        return "login";
    }

}