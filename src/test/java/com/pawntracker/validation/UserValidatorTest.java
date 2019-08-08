package com.pawntracker.validation;

import com.pawntracker.entity.PhoneNumber;
import com.pawntracker.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidatorTest {

    @Autowired
    UserValidator validator;


    User user;


    @Before
    public void setUp() {

         user = new User();
    }

    @Test
    public void supportsUserObject() {
        boolean supports = validator.supports(User.class);
        assertEquals(supports, true);
    }

    @Test
    public void supportsNotUserObject() {
        boolean supports = validator.supports(Object.class);
        assertEquals(supports, false);

    }

    @Test
    public void validate() {
        user.setPassword("password");
        user.setConfirmPassword("password");
        Errors errors = new BindException(user , "user");
        validator.validate(user, errors);
        assertEquals(errors.hasErrors(), false);
    }

    @Test
    public void validateWIthEmptyPassword() {
        user.setPassword("");
        user.setConfirmPassword("password");
        Errors errors = new BindException(user , "user");
        validator.validate(user, errors);
        assertEquals(errors.hasErrors(), true);
    }

    @Test
    public void validateWithEmptyConfirmPassowrd() {
        user.setPassword("password");
        user.setConfirmPassword("");
        Errors errors = new BindException(user , "user");
        validator.validate(user, errors);
        assertEquals(errors.hasErrors(), true);
    }
}