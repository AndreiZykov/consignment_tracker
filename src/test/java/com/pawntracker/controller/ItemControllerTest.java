package com.pawntracker.controller;

import com.pawntracker.service.ItemService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemControllerTest {

    @MockBean
    ItemService itemService;

    MockMvc mockMvc;

    @Test
    public void itemList() throws Exception {
        mockMvc.perform(get("/items/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("items"))
                .andExpect(model().attributeExists("text"))
                .andExpect(view().name("items/all_items"));
    }
}
