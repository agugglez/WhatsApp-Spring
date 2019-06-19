package com.springchatapp.backend.repository;

import com.springchatapp.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, Long> {
}
