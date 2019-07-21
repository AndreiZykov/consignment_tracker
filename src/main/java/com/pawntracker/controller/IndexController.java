package com.pawntracker.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {
    @GetMapping("/")
    public String indexPage(@RequestParam(name="name", required=false) String name, Model model) {
        name = "Pawn & consignment shop tracker application";
        model.addAttribute("name", name);
        return "index";
    }
}
