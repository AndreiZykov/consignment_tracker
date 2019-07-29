package com.pawntracker.repository;

import com.pawntracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User getUserByUsername(String username);
    List<User> findByApprovedFalse();
}
