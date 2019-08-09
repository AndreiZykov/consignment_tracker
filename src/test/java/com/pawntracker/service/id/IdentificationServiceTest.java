package com.pawntracker.service.id;

import com.pawntracker.entity.User;
import com.pawntracker.entity.id.DriversLicense;
import com.pawntracker.entity.id.Identification;
import com.pawntracker.entity.id.IdentificationCard;
import com.pawntracker.entity.id.Passport;
import com.pawntracker.repository.id.DriversLicenseRepository;
import com.pawntracker.repository.id.IdentificationCardRepository;
import com.pawntracker.repository.id.PassportRepository;
import com.pawntracker.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentificationServiceTest {

    @MockBean
    IdentificationCardRepository identificationCardRepository;

    @MockBean
    DriversLicenseRepository driversLicenseRepository;

    @MockBean
    PassportRepository passportRepository;

    @MockBean
    UserService userService;

    @Autowired
    IdentificationService identificationService;

    User user;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("test@test.com");
        Identification identification = new Identification();
        identification.setDriversLicenseList(new ArrayList<>());
        identification.setIdentificationCardList(new ArrayList<>());
        identification.setPassportList(new ArrayList<>());
        user.setIdentification(identification);

        when(userService.findByUsername(user.getUsername())).thenReturn(user);
    }


    @Test
    public void addIdCardToUser() {
        IdentificationCard card = new IdentificationCard();
        card.setId(0l);
        identificationService.addIdCardToUser(card, user.getUsername());
        System.out.println(user.getIdentification().getIdentificationCardList().isEmpty());
        assertEquals(user.getIdentification().getIdentificationCardList().isEmpty(), false);
    }

    @Test
    public void addDlToUser() {
        DriversLicense driversLicense = new DriversLicense();
        identificationService.addDlToUser(driversLicense, user.getUsername());
        assertEquals(user.getIdentification().getDriversLicenseList().isEmpty(), false);
    }

    @Test
    public void addPassportToUser() {
        Passport passport = new Passport();
        identificationService.addPassportToUser(passport, user.getUsername());
        assertEquals(user.getIdentification().getPassportList().isEmpty(), false);
    }
}