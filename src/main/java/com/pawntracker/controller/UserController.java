package com.pawntracker.controller;

import com.pawntracker.entity.User;
import com.pawntracker.service.SecurityService;
import com.pawntracker.service.UserService;
import com.pawntracker.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

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
    public String registration(@ModelAttribute("user") @Valid  User user, BindingResult bindingResult , @RequestParam("file") MultipartFile file) throws IOException {
        userValidator.validate(user,bindingResult);
        final String rawPassword = user.getPassword();
        if (bindingResult.hasErrors()) {
            System.out.println("errors" + bindingResult.getAllErrors());
            return "registration";
        }

        userService.saveUserOrUpdate(user);
        System.out.println(user.getUsername()+ " " + rawPassword);
        securityService.autoLogin(user.getUsername(), rawPassword);
        userService.addImage(user.getId(), file);

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

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        User user =  userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/user/uploadpicture/{id}")
    public String uploadPictureForProfile(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @PathVariable Long id) throws IOException {
        System.out.println("invoked");
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/profile";
        }
        userService.addImage(id, file);
       // redirectAttributes.addFlashAttribute("message",
       //         "You successfully uploaded '" + file.getOriginalFilename() + "'");

        return "redirect:/profile";
    }
}