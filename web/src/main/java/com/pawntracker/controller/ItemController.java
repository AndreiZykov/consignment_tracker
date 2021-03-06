package com.pawntracker.controller;

import com.pawntracker.entity.Item;
import com.pawntracker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;


@Controller
public class ItemController {

    @Value("${upload.path}")
    private String folder;

    @Autowired
    private ItemService itemService;

    @GetMapping("/items/all")
    public String itemList(Principal principal, Model model) {
        Iterable<Item> items = itemService.getAllApprovedItems();
        model.addAttribute("text", "All items list");
        model.addAttribute("items", items);

        return "items/all_items";
    }

    @GetMapping("/items/{id}")
    public String getItem(@PathVariable Long id, Model model) {
        Item item = itemService.getItem(id);
        String folderPath = folder;
        model.addAttribute("path" , folderPath);
        model.addAttribute("item", item);
        return "items/item";
    }


    @GetMapping("/items/create")
    public String createForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        File file = new File(folder);
        model.addAttribute("files", file.listFiles());
        return "items/createForm";
    }


    @PostMapping ("/items/create")
    public String saveNewItem(@Valid Item item, BindingResult result, Principal principal,
                              @RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
        if (result.hasErrors()) return "items/createForm";
        Item item1 =  itemService.save(item, principal.getName());
        for(MultipartFile file : uploadingFiles) {
            itemService.addImage(item1.getId(), file);
        }
        return "redirect:/items/all";
    }

    @GetMapping("/myitems")
    public String myItemList(Principal principal, Model model) {
        Iterable<Item> items = itemService.getItemsForUser(principal.getName());
        model.addAttribute("text", "My items list");
        model.addAttribute("items", items);

        return "items/all_items";
    }

    @GetMapping("/delete/item/{id}")
    public String delete(@PathVariable Long id, HttpServletRequest request) {
        itemService.delete(id);
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

}
