package com.springchatapp.backend.repository;

import com.springchatapp.backend.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
