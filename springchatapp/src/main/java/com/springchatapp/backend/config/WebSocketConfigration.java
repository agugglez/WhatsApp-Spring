package com.springchatapp.backend.config;

import com.springchatapp.backend.utility.CustomHandshakeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigration implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chatservice") //set websocket end point to connect to
        .setHandshakeHandler(new CustomHandshakeHandler())//set custom handshake handler
        .setAllowedOrigins("*")
        .withSockJS();
        
    }
    

    @Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
        //destinations start with "/topic" or "/queue" will be 
        //routed to the message broker (i.e. broadcasting to other connected clients):
        config.enableSimpleBroker("/topic");
        //destination starts with "/app" are routed to message-handling methods to controllers
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

}