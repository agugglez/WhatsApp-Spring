package com.springchatapp.backend.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController{

    private final SimpMessagingTemplate template;

    //@Autowired
    //private OnlineSession session;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/message")
    public void onReciveMessage(SimpMessageHeaderAccessor sha , String message){
        
         System.out.println(message);
         this.template.convertAndSendToUser(sha.getUser().getName() , "/topic/message",new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + message);
         
    }
}