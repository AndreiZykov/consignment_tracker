package com.pawntracker.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {
    @GetMapping("/signin")
    public String signInPage( Model model ) {

        return "signin";
    }



}
