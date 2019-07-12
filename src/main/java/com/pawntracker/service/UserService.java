package com.pawntracker.service;

import com.pawntracker.entity.User;
import com.pawntracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUserOrUpdate(User newUser) {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
