package com.ericampire.app.model.repository;

import com.ericampire.app.model.entity.Groupe;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Groupe, Long> {
}
