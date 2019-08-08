package com.pawntracker.controller;

import com.pawntracker.service.SecurityService;
import com.pawntracker.service.UserService;
import com.pawntracker.validation.UserValidator;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    SecurityService securityService;

    @MockBean
    UserValidator userValidator;



    @Test
    public void login() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/login");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void profile() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/profile");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void uploadPictureForProfile() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/user/uploadpicture");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }
}