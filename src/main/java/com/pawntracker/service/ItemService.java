package com.pawntracker.service;


import com.pawntracker.entity.Item;
import com.pawntracker.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {


    @Autowired
    private ItemRepository itemRepository;

    private Item save(Item item) {
        return itemRepository.save(item);
    }

    private Item getById(Long id) {
        return itemRepository.getById(id);
    }

    private void delete(Item item) {
        itemRepository.delete(item);
    }
}
