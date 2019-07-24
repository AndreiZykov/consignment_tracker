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


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid  User user, BindingResult bindingResult ,
                               @RequestParam("file") MultipartFile file) throws IOException {

        userValidator.validate(user,bindingResult);
        final String rawPassword = user.getPassword();
        if (bindingResult.hasErrors()) {
            System.out.println("errors" + bindingResult.getAllErrors());
            return "registration";
        }
        User user1 = userService.addImage(user, file);
        userService.saveUserOrUpdate(user1);
        securityService.autoLogin(user.getUsername(), rawPassword);

        return "redirect:/registration/second_stage";
    }

    @GetMapping("/registration/second_stage")
    public String secondStageRegistration(Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("phoneNumber", new PhoneNumber());
        return "registration/second_stage";
    }

    @PostMapping("/registration/second_stage")
    public String secondStageRegistration(Principal principal, @Valid Address address, @Valid PhoneNumber phoneNumber,
                                          BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("errors" + result.getAllErrors());
            return "registration/second_stage";
        }
        userService.addAddresAndPhoneNumberToUser(address, phoneNumber, principal.getName());
        return "redirect:/registration/choose-type-of-document";
    }

    @GetMapping("/registration/choose-type-of-document")
     public String chooseDocument() {
        return "registration/choose_document";
    }

    @GetMapping("/registration/add-id-card")
    public String documentStageRegistrationAddIdCard(Model model) {
        model.addAttribute("idcard", new IdentificationCard());
        return "registration/document_stage";
    }
    @GetMapping("/registration/add-dl")
    public String documentStageRegistrationAddDL(Model model) {
        model.addAttribute("dl", new DriversLicense());
        return "registration/document_stage";
    }
    @GetMapping("/registration/add-passport")
    public String documentStageRegistrationAddPassport(Model model) {
        model.addAttribute("idcard", new Passport());
        return "registration/document_stage";
    }

    @PostMapping("/registration/add-id-card")
    public String documentStageRegistrationAddIdCard(@Valid IdentificationCard card, BindingResult result, Principal principal) {
        return "redirect:/";

    }
    @PostMapping("/registration/add-dl")
    public String documentStageRegistrationAddDL(@Valid DriversLicense dl, BindingResult result, Principal principal) {
        return "redirect:/";
    }
    @PostMapping("/registration/add-passport")
    public String documentStageRegistrationAddPassport(@Valid Passport passport, BindingResult result, Principal principal) {
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
        User user = userService.getUserById(id);
        User user1 = userService.addImage(user, file);
        userService.saveUserOrUpdate(user1);
        return "redirect:/profile";
    }
}