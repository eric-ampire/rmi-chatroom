package com.ericampire.app.model.repository;

import com.ericampire.app.model.entity.Groupe;
import com.ericampire.app.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groupe, Long> {
}
