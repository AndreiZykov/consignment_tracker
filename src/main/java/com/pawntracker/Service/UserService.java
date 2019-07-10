package com.pawntracker.service;

import com.pawntracker.entity.User;
import com.pawntracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUserOrUpdate(User user) {
        return  userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
