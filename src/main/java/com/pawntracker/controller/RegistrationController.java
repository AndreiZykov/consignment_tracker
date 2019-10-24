package com.pawntracker.controller;

import com.pawntracker.entity.Address;
import com.pawntracker.entity.PhoneNumber;
import com.pawntracker.entity.User;
import com.pawntracker.entity.id.DriversLicense;
import com.pawntracker.entity.id.Identification;
import com.pawntracker.entity.id.IdentificationCard;
import com.pawntracker.entity.id.Passport;
import com.pawntracker.service.SecurityService;
import com.pawntracker.service.UserService;
import com.pawntracker.service.id.IdentificationService;
import com.pawntracker.validation.ImagesValidation;
import com.pawntracker.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.HTMLDocument;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private IdentificationService identificationService;

    @Autowired
    private ImagesValidation imagesValidation;

    @Autowired
    public RegistrationController(UserService userService, SecurityService securityService, UserValidator userValidator) {
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
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult)  {
        System.out.println("FIRED");
        userValidator.validate(user,bindingResult);
        final String rawPassword = user.getPassword();
        if (bindingResult.hasErrors()) {
            System.out.println("errors" + bindingResult.getAllErrors());
            return "registration";
        }
        userService.saveUserOrUpdate(user);
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
    public String secondStageRegistration(@Valid @ModelAttribute("address") Address address,BindingResult result,
                                          @Valid @ModelAttribute("phoneNumber")  PhoneNumber phoneNumber, BindingResult bindingResult ,
                                          Principal principal) {
        if (result.hasErrors() || bindingResult.hasErrors()) {
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
    public String documentStageRegistrationAddIdCard(Model model, Principal principal) {
        model.addAttribute("idcard", new IdentificationCard());

        User user = userService.findByUsername(principal.getName());
        Iterator iterator = getAttributes(user).entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            model.addAttribute((String) pair.getKey(), pair.getValue());
            iterator.remove();
        }
        return "registration/add-id-card";
    }

    @GetMapping("/registration/add-dl")
    public String documentStageRegistrationAddDL(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        Iterator iterator = getAttributes(user).entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            model.addAttribute((String) pair.getKey(), pair.getValue());
            iterator.remove();
        }

        model.addAttribute("dl", new DriversLicense());
        return "registration/add-dl";
    }

    @GetMapping("/registration/add-passport")
    public String documentStageRegistrationAddPassport(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("givenName", user.getFirstName());
        model.addAttribute("surname", user.getLastName());
        model.addAttribute("passport", new Passport());
        return "registration/add-passport";
    }

    @PostMapping("/registration/add-id-card")
    public String documentStageRegistrationAddIdCard(@Valid @ModelAttribute("idcard") IdentificationCard card, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "registration/add-id-card";
        }
        identificationService.addIdCardToUser(card, principal.getName());
        return "redirect:/registration/add-photo";

    }

    @PostMapping("/registration/add-dl")
    public String documentStageRegistrationAddDL(@Valid  @ModelAttribute("dl") DriversLicense dl, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "registration/add-dl";
        }
        identificationService.addDlToUser(dl, principal.getName());
        return "redirect:/registration/add-photo";
    }


    @PostMapping("/registration/add-passport")
    public String documentStageRegistrationAddPassport(@Valid Passport passport, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "registration/add-passport";
        }
        identificationService.addPassportToUser(passport, principal.getName());
        return "redirect:/registration/add-photo";
    }

    @GetMapping("/registration/add-photo")
    public String addPhoto(Model model) {
        return "registration/add-photo";
    }


    @PostMapping("/registration/add-photo")
    public String addPhoto(@RequestParam("frontFile") MultipartFile frontFile,
                           @RequestParam("profileFile") MultipartFile profileFile, Principal principal) throws IOException {

        if (frontFile.isEmpty() || profileFile.isEmpty()) {

            return "/registration/add-photo";
        }
        userService.addImage(principal.getName(), frontFile, profileFile);
        return "redirect:/";
    }

    private String oneLineAddress(Address address) {
        return address.getFirstLine() + " " + address.getSecondLine() + " " + address.getCity()
                + " " + address.getState() + " " + address.getZip();
    }

    private Map<String, String> getAttributes(User user) {
        Map<String, String> map = new HashMap<>();
        String address = "";
        map.put("firstName", user.getFirstName());
        map.put("secondName", user.getLastName());
        if (!user.getAddressList().isEmpty()) {
            Address addressObject = user.getAddressList().get(user.getAddressList().size()-1);
            address = oneLineAddress(addressObject);
        }
        map.put("address", address);
        return map;
    }
}
