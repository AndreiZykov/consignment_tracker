package com.pawntracker.service;

import com.pawntracker.entity.Role;
import com.pawntracker.entity.User;
import com.pawntracker.repository.RoleRepository;
import com.pawntracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User saveUserOrUpdate(User newUser) {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            newUser.setRoles( new HashSet<>(roleRepository.findAll()));
            return userRepository.save(newUser);

    }

    public User findByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
