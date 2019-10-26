package com.pawntracker.service;

import com.pawntracker.entity.Address;
import com.pawntracker.entity.PhoneNumber;
import com.pawntracker.repository.PhoneNumberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneNumberServiceTest {

    @MockBean
    PhoneNumberRepository phoneNumberRepository;

    @Autowired
            PhoneNumberService phoneNumberService;

    PhoneNumber phoneNumber;

    @Before
    public void setUp() {
        phoneNumber = new PhoneNumber();
        phoneNumber.setId(1L);
    }


    @Test
    public void saveOrUpdate() {
        when(phoneNumberRepository.save(phoneNumber)).thenReturn(phoneNumber);
        PhoneNumber phoneNumber1 = phoneNumberService.saveOrUpdate(phoneNumber);
        assertEquals(phoneNumber1, phoneNumber);

    }

    @Test
    public void getPhoneNumberById() {
        when(phoneNumberRepository.getPhoneNumberById(phoneNumber.getId())).thenReturn(phoneNumber);
        PhoneNumber phoneNumber1 = phoneNumberService.getPhoneNumberById(phoneNumber.getId());
        assertEquals(phoneNumber1, phoneNumber);
    }

    @Test
    public void deletePhoneNumber() {
        when(phoneNumberRepository.getPhoneNumberById(phoneNumber.getId())).thenReturn(phoneNumber);

        phoneNumberRepository.delete(phoneNumber);

        verify(phoneNumberRepository, times(1)).delete(phoneNumber);
    }




}