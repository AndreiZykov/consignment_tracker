package com.pawntracker.controller;
import com.pawntracker.entity.Item;
import com.pawntracker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
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


    @GetMapping("/{id}")
    public String getItem(@PathVariable Long id, Principal principal, Model model) {
        Item item = itemService.getItem(id, principal.getName());
        model.addAttribute("item", item);
        return "items/item";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "items/createForm";
    }

    @PostMapping("")
    public String saveNewItem(@Valid Item item, BindingResult result, Principal principal, Model model) {
        if(result.hasErrors()) {
           return "items/createForm";
        }
        itemService.save(item, principal.getName());
        return "redirect:/all";
    }


}
