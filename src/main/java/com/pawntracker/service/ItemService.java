package com.pawntracker.service;


import com.pawntracker.entity.Item;
import com.pawntracker.entity.User;
import com.pawntracker.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;

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

    public Item save(Item item, String username) {
        User user = userService.findByUsername(username);
        item.setUser(user);
        List<Item> list = user.getItemList();
        list.add(item);
        user.setItemList(list);
        return itemRepository.save(item);
    }

    public Item getItem(Long id, String username ) {
        User user = userService.findByUsername(username);

        Item item = itemRepository.getById(id);
        if (item.getUser().equals(user)) return item;
        return null;
        // throw new exception("This Item doesn't belong to you")
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
