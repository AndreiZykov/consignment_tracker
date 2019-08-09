package com.pawntracker.controller;

import com.pawntracker.service.ItemService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @MockBean
    ItemService itemService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void itemList() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/all");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void getItem() throws Exception {
        Long id = 1L;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/" + id);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }


    @Test
    public void createForm() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/create");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void saveNewItem() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/items/create"  );
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void myItemList() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/myitems"  );
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void delete() throws Exception {
        Long id = 1L;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/delete/item" + id  );
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }



}