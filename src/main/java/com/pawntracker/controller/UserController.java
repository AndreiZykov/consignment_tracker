package com.pawntracker.controller;

import com.pawntracker.entity.Address;
import com.pawntracker.entity.PhoneNumber;
import com.pawntracker.entity.User;
import com.pawntracker.entity.id.DriversLicense;
import com.pawntracker.entity.id.IdentificationCard;
import com.pawntracker.entity.id.Passport;
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

import javax.jws.WebParam;
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

    @PostMapping("/user/uploadpicture")
    public String uploadPictureForProfile(@RequestParam("frontFile") MultipartFile frontFile, @RequestParam("profileFile") MultipartFile profileFile,
                                   RedirectAttributes redirectAttributes, Principal principal) throws IOException {

        if (frontFile.isEmpty() || profileFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/profile";
        }
        User user1 = userService.addImage(principal.getName(), frontFile, profileFile);
        userService.saveUserOrUpdate(user1);
        return "redirect:/profile";
    }
}