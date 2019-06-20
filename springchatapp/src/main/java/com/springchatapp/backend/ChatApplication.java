package com.springchatapp.backend;

import com.springchatapp.backend.model.Conversation;
import com.springchatapp.backend.model.Message;
import com.springchatapp.backend.model.UserProfile;
import com.springchatapp.backend.service.IConversationService;
import com.springchatapp.backend.service.IMessageService;
import com.springchatapp.backend.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication implements CommandLineRunner {

    private IUserProfileService userService;
    private IConversationService conversationService;
    private IMessageService messageService;

    @Autowired
    public ChatApplication(IUserProfileService userService, IConversationService conversationService, IMessageService messageService) {
        this.userService = userService;
        this.conversationService = conversationService;
        this.messageService = messageService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (userService.getAll().size() == 0) {

            UserProfile user1 = new UserProfile();
            user1.setName("Louis Litt");
            user1.setAvatar("http://emilcarlsson.se/assets/louislitt.png");
            user1.setPhoneString("123-456-7890");

            //user1 = userService.save(user1);

            UserProfile user2 = new UserProfile();
            user2.setName("Harvey Specter");
            user2.setAvatar("http://emilcarlsson.se/assets/harveyspecter.png");
            user2.setPhoneString("123-456-7891");

            //user2 = userService.save(user2);

//            UserProfile user3 = new UserProfile();
//            user3.setName("Rachel Zane");
//            user3.setAvatar("http://emilcarlsson.se/assets/rachelzane.png");
//            user3.setPhoneString("123-456-7892");

            //user3 = userService.save(user3);

            //add some contacts to user1
            user1.getContacts().add(user2);
            //user1.getContacts().add(user3);

            //add some contacts to user2
            user2.getContacts().add(user1);
            //user2.getContacts().add(user3);

            //add some contacts to user3
//            user3.getContacts().add(user1);
//            user3.getContacts().add(user2);

            user1 = userService.save((user1));
            user2 = userService.save((user2));
            //user3 = userService.save((user3));

            //print everybody
            userService.getAll().forEach(System.out::println);

            //create conversation and add users to it
            Conversation conversation1 = new Conversation();
            conversation1.getMembers().add(user1);
            conversation1.getMembers().add(user2);

            //create a new message and add it to the conversation
            Message message1 = new Message();
            message1.setFrom(user1);
            message1.setTxt("hello user2");

            message1.setConversation(conversation1);

            conversation1.getMessages().add((message1));

            conversationService.save(conversation1);

            messageService.save(message1);

        }

    }
}
