package com.pawntracker.controller;
import com.pawntracker.entity.Item;
import com.pawntracker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;


@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;



    @RequestMapping(value = "/items/all", method = RequestMethod.GET)
    public String itemList(Principal principal, Model model) {
        Iterable<Item> items = itemService.getAllItems();
        model.addAttribute("text", "All items list");
        model.addAttribute("items",items );

        return "items/all_items";
    }



    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    public String getItem(@PathVariable Long id,  Model model) {
        Item item = itemService.getItem(id);
        model.addAttribute("item", item);
        return "items/item";
    }


    @RequestMapping(value = "/items/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "items/createForm";
    }


    @RequestMapping(value = "/items/create", method = RequestMethod.POST)
    public String saveNewItem(@Valid Item item, BindingResult result, Principal principal, Model model) {
        if(result.hasErrors()) {
           return "items/createForm";
        }
        itemService.save(item, principal.getName());
        return "redirect:/items/all";
    }

    @RequestMapping(value = "/myitems", method = RequestMethod.GET)
    public String myItemList(Principal principal, Model model) {
        Iterable<Item> items = itemService.getItemsForUser(principal.getName());
        model.addAttribute("text", "My items list");
        model.addAttribute("items",items );

        return "items/all_items";
    }

    @RequestMapping(value = "/delete/item/{id}", method = RequestMethod.GET)
    public  String delete( @PathVariable Long id, HttpServletRequest request) {
        itemService.delete(id);
        String referrer = request.getHeader("referer");
        return "redirect:" +referrer;
    }




}
