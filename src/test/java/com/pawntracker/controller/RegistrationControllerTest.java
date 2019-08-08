package com.pawntracker.controller;

import com.pawntracker.entity.id.Identification;
import com.pawntracker.service.SecurityService;
import com.pawntracker.service.UserService;
import com.pawntracker.service.id.IdentificationService;
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
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {
    @MockBean
    UserService userService;

    @MockBean
    SecurityService securityService;

    @MockBean
    UserValidator userValidator;

    @MockBean
    IdentificationService identificationService;

    private final String url = "/registration/";



    @Autowired
    MockMvc mockMvc;

    @Test
    public void registration() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void registration1() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(url);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void secondStageRegistration() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "second_stage");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void secondStageRegistration1() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(url + "second_stage");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void chooseDocument() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "choose-type-of-document");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void documentStageRegistrationAddIdCard() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "add-id-card");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void documentStageRegistrationAddDL() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "add-dl");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void documentStageRegistrationAddPassport() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "add-passport");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void documentStageRegistrationAddIdCard1() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(url + "add-id-card");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void documentStageRegistrationAddDL1() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(url + "add-dl");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void documentStageRegistrationAddPassport1() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(url + "add-passport");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void addPhoto() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(url + "add-photo");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }

    @Test
    public void addPhoto1() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(url + "add-photo");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
    }
}