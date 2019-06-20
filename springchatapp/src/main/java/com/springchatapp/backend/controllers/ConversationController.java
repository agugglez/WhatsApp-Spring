package com.springchatapp.backend.controllers;

import com.springchatapp.backend.model.Conversation;
import com.springchatapp.backend.model.UserProfile;
import com.springchatapp.backend.service.IConversationService;
import com.springchatapp.backend.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ConversationController {

    private IConversationService conversationService;
    private IUserProfileService userProfileService;

    @Autowired
    public ConversationController(IConversationService conversationService,
                                  IUserProfileService userProfileService) {
        this.conversationService = conversationService;
        this.userProfileService = userProfileService;
    }

    @GetMapping(value = "/get/conversation")
    public Conversation getConversation(@RequestParam("from") long from, @RequestParam("to") long to) {
        //long idLong = Long.parseLong(phone);
        Conversation result = conversationService.findConversationByUsers(from, to);
        if (result == null) {
            result = new Conversation();

            UserProfile fromUser = userProfileService.getUserById(from).get();
            UserProfile toUser = userProfileService.getUserById(to).get();

            result.getMembers().add(fromUser);
            result.getMembers().add(toUser);

            result = conversationService.save(result);

        }

        return result;
    }
}
