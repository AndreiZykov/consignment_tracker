package com.pawntracker.controller;
import com.pawntracker.entity.Item;
import com.pawntracker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping("/all")
    public String itemList(Principal principal, Model model) {
        Iterable<Item> items = itemService.getItemsForUser(principal.getName());
        model.addAttribute("items",items );
        return "items/allItems";
    }
}
