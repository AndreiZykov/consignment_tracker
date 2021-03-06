package com.pawntracker.service;


import com.pawntracker.entity.Item;
import com.pawntracker.entity.User;
import com.pawntracker.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Value("${upload.path}")
    private String folder;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    public Iterable<Item> getItemsForUser(String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return user.getItemList();
        }
        return null;
    }

    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item save(Item item, String username) {
        User user = userService.findByUsername(username);
        item.setUser(user);
        List<Item> list = user.getItemList();
        list.add(item);
        user.setItemList(list);
        return itemRepository.save(item);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemForUser(Long id, String username) {
        User user = userService.findByUsername(username);

        Item item = itemRepository.getById(id);
        if (item.getUser().equals(user)) return item;
        return null;
        // throw new exception("This Item doesn't belong to you")
    }

    public Item getItem(Long id) {
        Item item = itemRepository.getById(id);
        return item;
    }

    public void delete(Long id) {
        Item item = itemRepository.getById(id);
        User user = item.getUser();
        List<Item> list = user.getItemList();
        list.remove(item);
        user.setItemList(list);
        userService.saveUserOrUpdate(user);
        itemRepository.delete(item);
    }

    public void addImage(Long id, MultipartFile file) throws IOException {
        Item item = itemRepository.getById(id);
        String fileName = item.getName() + "-" + item.getId() + "-" + item.getImagesPaths().size() + "-" + file.getOriginalFilename();
        Path path = Paths.get(folder + fileName);
        imageService.saveImage(path, file.getBytes());
        ArrayList<String> paths = item.getImagesPaths();
        paths.add(fileName);
        item.setImagesPaths(paths);
        itemRepository.save(item);
    }

    public Iterable<Item> getAllApprovedItems() {
        return itemRepository.findByApprovedTrue();
    }

    public Iterable<Item> getAllNonApprovedItems() {
        return itemRepository.findByApprovedFalse();
    }

    public void approveItem(Long id) {
        Item item = itemRepository.getById(id);
        item.setApproved(!item.isApproved());
        itemRepository.save(item);

    }

}
