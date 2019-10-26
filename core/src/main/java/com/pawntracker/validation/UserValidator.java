package com.pawntracker.validation;

import com.google.common.base.Strings;
import com.pawntracker.entity.User;
import com.pawntracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Nullable;

@Component
public class UserValidator implements Validator {

    public static final String USER_NAME_FIELD = "username";
    public static final String PASSWORD_FILED = "username";

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(@Nullable Object object, Errors errors) {

        User user = (User) object;

        final String password = Strings.nullToEmpty(user.getPassword());
        final String passwordConfirmation = Strings.nullToEmpty(user.getConfirmPassword());

        if (userRepository.getUserByEmail(user.getEmail()) != null) {
            errors.rejectValue(USER_NAME_FIELD, "unique", "Username already exist");
        }

        if (password.length() < 6) {
            errors.rejectValue(PASSWORD_FILED, "length", "Password must be at least 6 characters");
        }

        if (!password.equals(passwordConfirmation)){
            errors.rejectValue(PASSWORD_FILED, "match", "Password and confirmation must match");
        }

    }
}