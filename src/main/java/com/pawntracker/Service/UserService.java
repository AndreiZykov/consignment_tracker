package com.pawntracker.Service;

import com.pawntracker.Entity.User;
import com.pawntracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUserOrUpdate(User user) {
        return  userRepository.save(user);
    }

}
