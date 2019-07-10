package com.pawntracker.Repository;

import com.pawntracker.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
    User getUserById(Long id);
}
