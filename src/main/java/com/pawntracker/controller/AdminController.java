package com.pawntracker.controller;

import com.pawntracker.entity.Item;
import com.pawntracker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ItemService itemService;


    @GetMapping("/")
    public String dashboardPage() {
        return "admin/dashboard";
    }


    @GetMapping("/items/toapprove")
    public String listOfItemsToApprove(Model model) {
        Iterable<Item> items = itemService.getAllNonApprovedItems();
        model.addAttribute("items", items);
        return "admin/items_to_approve";
    }


    @GetMapping("/items/approve/{id}")
    public String approveSingleItem(@PathVariable Long id, HttpServletRequest request) {
        itemService.approveItem(id);

        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/items/all")
    public String listOfItems(Model model) {
        Iterable<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "admin/all_items";
    }


    @GetMapping("/items/{id}")
    public String item(@PathVariable Long id,  Model model) {
        Item item = itemService.getItem(id);
        model.addAttribute("item", item);
        return "admin/item";
    }
    @PostMapping("/items/update")
    public String item(@Valid Item item) {
        itemService.save(item);
        return "redirect:/admin/items/" + item.getId();
    }
}
