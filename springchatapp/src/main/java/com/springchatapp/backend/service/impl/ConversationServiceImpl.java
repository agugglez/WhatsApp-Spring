package com.springchatapp.backend.service.impl;

import com.springchatapp.backend.model.Conversation;
import com.springchatapp.backend.model.UserProfile;
import com.springchatapp.backend.repository.ConversationRepository;
import com.springchatapp.backend.service.IConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Conversation findConversationByUsers(long fromUser, long toUser) {

        for (Conversation conv :
                conversationRepository.findAll()) {

            boolean fromFound = false;

            for (UserProfile u :
                    conv.getMembers()) {
                if (u.getUserID() == fromUser) {
                    fromFound = true;
                    break;
                }
            }

            if (fromFound) {
                //search toUser
                for (UserProfile u :
                        conv.getMembers()) {
                    if (u.getUserID() == toUser) {
                        return conv;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public Optional<Conversation> findById(Long convId) {
        return conversationRepository.findByConversationId(convId);
    }
}
