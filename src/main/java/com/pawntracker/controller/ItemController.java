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

    @RequestMapping(value = "/items/all", method = RequestMethod.GET)
    public String itemList(Principal principal, Model model) {
        Iterable<Item> items = itemService.getAllItems();
        model.addAttribute("text", "All items list");
        model.addAttribute("items", items);

        return "items/all_items";
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    public String getItem(@PathVariable Long id, Model model) {
        Item item = itemService.getItem(id);
        String folderPath = folder;
        model.addAttribute("path" , folderPath);
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
        if (result.hasErrors()) {
            return "items/createForm";
        }
        itemService.save(item, principal.getName());
        return "redirect:/items/all";
    }

    @RequestMapping(value = "/myitems", method = RequestMethod.GET)
    public String myItemList(Principal principal, Model model) {
        Iterable<Item> items = itemService.getItemsForUser(principal.getName());
        model.addAttribute("text", "My items list");
        model.addAttribute("items", items);

        return "items/all_items";
    }

    @RequestMapping(value = "/delete/item/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, HttpServletRequest request) {
        itemService.delete(id);
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/items/upload")
    public String upload() {

        return "items/upload";
    }


    @PostMapping("/items/upload/{id}")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @PathVariable Long id) throws IOException {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/";
        }

        byte[] bytes = file.getBytes();

        itemService.addImage(id, file, bytes);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");

        return "redirect:/";
    }


}
