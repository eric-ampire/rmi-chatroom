package com.ericampire.app.model.repository;

import com.ericampire.app.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
