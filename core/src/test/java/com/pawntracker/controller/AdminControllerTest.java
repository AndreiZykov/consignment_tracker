package com.pawntracker.controller;

import com.pawntracker.service.ItemService;
import com.pawntracker.service.UserService;
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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    ItemService itemService;

    @Autowired
    MockMvc mockMvc;

    final String url = "/admin/";


    @Test
    public void dashboardPage() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }


    @Test
    public void listOfItemsToApprove() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "/items/toapprove");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void approveSingleItem() throws Exception {
        Long id = 1L;
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "/items/approve/" + id);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void listOfItems() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "/items/all");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void item() throws Exception {
        Long id = 1L;
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "/items/" + id);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void item1() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(url + "/items/update");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);

    }

    @Test
    public void usersToApprove() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "/users/toapprove");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void approveUser() throws Exception {
        Long id = 1L;
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "/users/toapprove/" + id);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }
}