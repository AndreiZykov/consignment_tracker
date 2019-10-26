package com.pawntracker.service;

import com.pawntracker.entity.User;
import com.pawntracker.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomUserDetailsServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    User user;
    @Before
    public void setUp() {
        user = new User();

        user.setUsername("test@test.com");
    }

    @Test
    public void loadUserByUsername() {
        when(userRepository.getUserByUsername(user.getUsername())).thenReturn(user);
        User user1 = (User) customUserDetailsService.loadUserByUsername(user.getUsername());
        assertEquals(user1, user);
    }
}