package com.springchatapp.backend.service.impl;

import com.springchatapp.backend.model.Conversation;
import com.springchatapp.backend.repository.ConversationRepository;
import com.springchatapp.backend.service.IConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements IConversationService {

    private ConversationRepository conversationRepository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public List<Conversation> getAll() {
        return conversationRepository.findAll();
    }

    @Override
    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }
}
