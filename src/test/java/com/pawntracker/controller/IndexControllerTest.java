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
@WebMvcTest(IndexController.class)
public class IndexControllerTest {


    @Autowired
    MockMvc mockMvc;


    @Test
    public void indexPage() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }
}