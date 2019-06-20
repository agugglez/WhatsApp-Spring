package com.springchatapp.backend.service;

import com.springchatapp.backend.model.Conversation;
import com.springchatapp.backend.model.UserProfile;

import java.util.List;
import java.util.Optional;

public interface IConversationService {
    List<Conversation> getAll();

    Conversation save(Conversation conversation);

    Conversation findConversationByUsers(long fromId, long toId);

    Optional<Conversation> findById(Long convId);
}
