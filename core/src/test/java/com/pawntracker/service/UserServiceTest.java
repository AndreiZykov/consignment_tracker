package com.pawntracker.service;

import com.pawntracker.entity.Address;
import com.pawntracker.entity.PhoneNumber;
import com.pawntracker.entity.Role;
import com.pawntracker.entity.User;
import com.pawntracker.repository.*;
import com.pawntracker.repository.id.IdentificationCardRepository;
import com.pawntracker.repository.id.IdentificationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    ImageService imageService;

    @MockBean
    PhoneNumberRepository phoneNumberRepository;

    @MockBean
    IdentificationRepository identificationRepository;

    @MockBean
    AddressRepository addressRepository;


    @MockBean
    PhotographRepository photographRepository;

    @Autowired
    private UserService userService;


    User user;
    Role role;

    @Before
    public void setUp() {
        user = new User();
        role = new Role();
        role.setName("USER");
        user.setUsername("test@test.com");
        user.setId(0l);
    }


    @Test
    public void saveUserOrUpdate() {
        when(userRepository.getUserByUsername(user.getUsername())).thenReturn(user);
        when(roleRepository.getByName("USER")).thenReturn(role);
        User user1 = userService.saveUserOrUpdate(user);
        assertNotEquals(user1,user);
    }

    @Test
    public void findByUsername() {
        when(userRepository.getUserByUsername(user.getUsername())).thenReturn(user);
        User user1 = userService.findByUsername(user.getUsername());
        assertEquals(user1, user);
    }

    @Test
    public void getUserById() {
        when(userRepository.getUserById(user.getId())).thenReturn(user);
        User user1 = userService.getUserById(user.getId());
        assertEquals(user1, user);
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void addImage() {
    }

    @Test
    public void addAddresAndPhoneNumberToUser() {
        when(userRepository.getUserByUsername(user.getUsername())).thenReturn(user);
        Address address = new Address();
        PhoneNumber phoneNumber = new PhoneNumber();
        userService.addAddresAndPhoneNumberToUser(address, phoneNumber, user.getUsername());
        assertEquals(user.getAddressList().isEmpty(), false );
    }

    @Test
    public void approveUser() {
        when(userRepository.getUserById(user.getId())).thenReturn(user);
        user.setApproved(false);
        userService.approveUser(user.getId());
        assertEquals(user.isApproved(), true);
    }

    @Test
    public void userListToApprove() {
        List<User > usersToApprove=new ArrayList<>();
        usersToApprove.add(user);
        when(userRepository.findByApprovedFalse()).thenReturn(usersToApprove);
        List<User> returnList = userService.userListToApprove();
        assertEquals(usersToApprove, returnList);
    }

    @Test
    public void getStageOfRegistration() {
        when(userRepository.getUserByUsername(user.getUsername())).thenReturn(user);
        String stage = userService.getStageOfRegistration(user.getUsername());
        assertEquals(stage, "address");
    }
}