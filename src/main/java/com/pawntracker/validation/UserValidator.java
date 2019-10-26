package com.pawntracker.validation;

import com.google.common.base.Strings;
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

        final String password = Strings.nullToEmpty(user.getPassword());
        final String passwordConfirmation = Strings.nullToEmpty(user.getConfirmPassword());

        if (user1 != null) {
            errors.rejectValue("username", "unique", "Username must be unique");
        }

        if (password.length() < 6) {
            errors.rejectValue("password", "length", "Password must be at least 6 characters");
        }

        if (!password.equals(passwordConfirmation)){
            errors.rejectValue("confirmPassword", "Match", "Password must match");
        }

    }
}