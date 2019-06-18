package com.springchatapp.backend.service;

import com.springchatapp.backend.model.Conversation;

import java.util.List;

public interface IConversationService {
    List<Conversation> getAll();

    Conversation save(Conversation conversation);

}
