package com.ericampire.app.model.repository;

import com.ericampire.app.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUserNameAndPassword(String username, String password);
}
