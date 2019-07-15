package com.pawntracker.service;


import com.pawntracker.entity.Item;
import com.pawntracker.entity.User;
import com.pawntracker.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;

@Service
public class ItemService {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    public Iterable<Item> getItemsForUser(String username) {
        User user = userService.findByUsername(username);
        if (user!= null) {
            return user.getItemList();
        }
        return null;
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item getById(Long id) {
        return itemRepository.getById(id);
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
