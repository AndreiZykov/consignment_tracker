package com.pawntracker.service;

import com.pawntracker.entity.Item;
import com.pawntracker.entity.User;
import com.pawntracker.repository.ItemRepository;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
       user.setItemList( items);

   }
    @Test
    public void getItemsForUser() {
       when(userService.findByUsername(any())).thenReturn(user);
       when(itemsRepository.findByApprovedTrue()).thenReturn(items);
       List<Item> itemList = (List<Item>) itemService.getAllApprovedItems();
       assertEquals(items, itemList);
    }

    @Test
    public void getAllItems() {
       when(itemsRepository.findAll()).thenReturn(items);

       List<Item> returnItems = (List<Item>) itemService.getAllItems();
       assertEquals(items, returnItems);
    }

    @Test
    public void save() {
        Item item = new Item();
       when(itemsRepository.save(item)).thenReturn(item);
        when(userService.findByUsername(user.getUsername())).thenReturn(user);

      Item returnItem =  itemService.save(item, user.getUsername());
      assertEquals(item, returnItem);

   }

    @Test
    public void save1() {
        Item item = new Item();
        when(itemsRepository.save(item)).thenReturn(item);
        Item returnItem = itemService.save(item);
        assertEquals(item, returnItem);
    }

    @Test
    public void getItemForUser() {
       Item item = new Item();
       item.setId(1L);
       item.setUser(user);
       when(userService.findByUsername(user.getUsername())).thenReturn(user);
       when(itemsRepository.getById(item.getId())).thenReturn(item);


       Item returnItem = itemService.getItemForUser(item.getId(), user.getUsername());
       assertEquals(item, returnItem);
    }

    @Test
    public void getItem() {
       Item item = new Item();
       item.setId(1L);

       when(itemsRepository.getById(item.getId())).thenReturn(item);

       Item item1 = itemService.getItem(item.getId());
       assertEquals(item, item1);
    }

    @Test
    public void delete() {
       Item item = new Item();
       item.setId(1L);
       item.setUser(user);
       List<Item> list = user.getItemList();
       list.add(item);
       user.setItemList(list);
       when(itemsRepository.getById(item.getId())).thenReturn(item);

    }

    @Test
    public void addImage() throws IOException {

    }

    @Test
    public void getAllApprovedItems() {
       when(itemsRepository.findByApprovedTrue()).thenReturn(items);
       List<Item> returnItems = (List<Item>) itemService.getAllApprovedItems();
       assertEquals(items, returnItems);
    }

    @Test
    public void getAllNonApprovedItems() {
        when(itemsRepository.findByApprovedFalse()).thenReturn(items);
        List<Item> returnItems = (List<Item>) itemService.getAllApprovedItems();
        assertEquals(items, returnItems);
    }

    @Test
    public void approveItem() {
       Item item = new Item();
       item.setId(1L);
       when(itemsRepository.getById(item.getId())).thenReturn(item);

    }
}
