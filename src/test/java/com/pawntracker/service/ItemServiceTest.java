package com.pawntracker.service;

import com.pawntracker.entity.Item;
import com.pawntracker.entity.User;
import com.pawntracker.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {
   @MockBean
   ItemRepository itemsRepository;

   @MockBean
   UserService userService;

   @Autowired
   private ItemService itemService;

   User user= new User();

   List<Item> items = new ArrayList<>();


   @Before
   public void setUp() {
       user.setUsername("test@test.com");

   }
    @Test
    public void getItemsForUser() {
       when(userService.findByUsername(any())).thenReturn(user);
       when(itemsRepository.findByApprovedTrue()).thenReturn(items);
       List<Item> itemList = (List<Item>) itemService.getAllApprovedItems();
       assertEquals(items, itemList);
    }
}
