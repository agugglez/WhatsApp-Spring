package com.springchatapp.backend.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.springchatapp.backend.basicmodel.BasicMessage;
import com.springchatapp.backend.model.Conversation;
import com.springchatapp.backend.model.Message;
import com.springchatapp.backend.model.UserProfile;
import com.springchatapp.backend.service.IConversationService;
import com.springchatapp.backend.service.IMessageService;
import com.springchatapp.backend.utility.OnlineSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;
    private OnlineSession onlineSession;

    private IConversationService conversationService;
    private IMessageService messageService;

    @Autowired
    WebSocketController(SimpMessagingTemplate template, OnlineSession onlineSession,
                        IConversationService conversationService, IMessageService messageService) {
        this.template = template;
        this.onlineSession = onlineSession;
        this.conversationService = conversationService;
        this.messageService = messageService;
    }

    @MessageMapping("/message")
    public void onReciveMessage(SimpMessageHeaderAccessor sha, Message message) {
        System.out.println(message);

        Conversation parentConversation = conversationService.findById(message.getConversationId()).get();
        Set<UserProfile> members = parentConversation.getMembers();

        UserProfile sender = null;
        UserProfile targetUser = null;

        //find the target user
        for (UserProfile up :
                members) {
            if (up.getUserID() != message.getFrom().getUserID())
                targetUser = up;
            else sender = up;
        }

        System.out.println("targetUser: " + targetUser.getUserID());
        String targetSession = onlineSession.getSessionMap().get(targetUser.getUserID());

        //sha.getUser().getName()
        System.out.println(targetSession);
        this.template.convertAndSendToUser(targetSession, "/topic/message", message);

        //create a new message and add it to the conversation
        Message message1 = new Message();
        message1.setFrom(sender);
        message1.setTxt(message.getTxt());

        message1.setConversation(parentConversation);

        parentConversation.getMessages().add((message1));

        conversationService.save(parentConversation);

        messageService.save(message1);

    }

    @MessageMapping("/register")
    public void registerSession(SimpMessageHeaderAccessor sha, Long userId) {
        System.out.println("getName: " + sha.getUser().getName() + " userID: " + userId);
        onlineSession.getSessionMap().put(userId, sha.getUser().getName());
//        if (onlineSession.getSessionMap().containsKey(userId)) {
//
//        }
    }
}