package com.pawntracker.controller;

import com.pawntracker.entity.Item;
import com.pawntracker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private ItemService itemService;


    @GetMapping("/")
    public String dashboardPage() {
        return "owner/dashboard";
    }


    @GetMapping("/items/toapprove")
    public String listOfItemsToApprove(Model model) {
        Iterable<Item> items = itemService.getAllNonApprovedItems();
        model.addAttribute("items", items);
        return "owner/items_to_approve";
    }
}
