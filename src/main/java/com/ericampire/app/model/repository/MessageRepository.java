package com.ericampire.app.model.repository;

import com.ericampire.app.model.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
