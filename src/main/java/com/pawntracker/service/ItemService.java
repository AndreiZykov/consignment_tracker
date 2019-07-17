package com.pawntracker.service;


import com.pawntracker.entity.Item;
import com.pawntracker.entity.User;
import com.pawntracker.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.io.IOException;
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

    public Item getItemForUser(Long id, String username ) {
        User user = userService.findByUsername(username);

        Item item = itemRepository.getById(id);
        if (item.getUser().equals(user)) return item;
        return null;
        // throw new exception("This Item doesn't belong to you")
    }

     public Item getItem(Long id ) {

        Item item = itemRepository.getById(id);
        return item;
        // throw new exception("This Item doesn't belong to you")
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

   public void saveImageFile(Long id, MultipartFile file) {
       try {
           Item recipe = itemRepository.getById(id);

           Byte[] byteObjects = new Byte[file.getBytes().length];

           int i = 0;

           for (byte b : file.getBytes()){
               byteObjects[i++] = b;
           }

           recipe.setImage(byteObjects);

           itemRepository.save(recipe);
       } catch (IOException e) {
           //todo handle better
          // log.error("Error occurred", e);

           e.printStackTrace();
       }
   }
}
