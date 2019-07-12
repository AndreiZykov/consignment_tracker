package com.pawntracker.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signin")
public class SignInController {
    @GetMapping("")
    public String indexPage( Model model ) {

        return "signin";
    }

}
