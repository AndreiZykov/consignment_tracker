package com.pawntracker.service;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;


import com.pawntracker.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityServiceTest {

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    CustomUserDetailsService userDetailsService;

    @Mock
    SecurityContext mockSecurityContext;

    @Autowired
    private SecurityService securityService;


    private AuthenticationManager manager;

    private BasicAuthenticationFilter filter;


    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("test@test.com");
        user.setPassword("password");

        SecurityContextHolder.clearContext();
        UsernamePasswordAuthenticationToken rodRequest = new UsernamePasswordAuthenticationToken(
                "rod", "koala");
        rodRequest.setDetails(new WebAuthenticationDetails(new MockHttpServletRequest()));
        Authentication rod = new UsernamePasswordAuthenticationToken("rod", "koala",
                AuthorityUtils.createAuthorityList("ROLE_1"));

        manager = mock(AuthenticationManager.class);
        when(manager.authenticate(rodRequest)).thenReturn(rod);
        when(manager.authenticate(not(eq(rodRequest)))).thenThrow(
                new BadCredentialsException(""));

        filter = new BasicAuthenticationFilter(manager,
                new BasicAuthenticationEntryPoint());
    }

    @Test
    public void findLoggedInUsername() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);


        when(securityContext.getAuthentication().getDetails())
                .thenReturn(user);


        String username = securityService.findLoggedInUsername();
        System.out.println(username);
        assertEquals(user.getUsername(), username);
    }

    @Test
    public void autoLogin() {
        when(userDetailsService.loadUserByUsername(user.getUsername())).thenReturn(user);

       ;

        securityService.autoLogin(user.getUsername(), user.getPassword());
    }
}