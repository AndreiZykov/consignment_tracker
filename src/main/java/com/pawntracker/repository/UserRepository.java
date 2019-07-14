package com.pawntracker.repository;

import com.pawntracker.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
    User getUserById(Long id);
    User getUserByUsername(String username);
}
