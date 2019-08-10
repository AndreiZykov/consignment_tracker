package com.pawntracker.validation;

import com.pawntracker.entity.User;
import com.pawntracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;
        User user1 = userRepository.getUserByUsername(user.getUsername());
        if (user1 != null) {

            errors.rejectValue("username", "unique", "Username must be unique");
        }
        if (user.getPassword().length()< 6) {
            errors.rejectValue("password", "length", "Password must be at least 6 characters");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "Match", "Password must match");
        }
    }
}