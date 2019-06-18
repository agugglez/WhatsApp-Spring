package com.springchatapp.backend.springchatapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int sessionID;

    @Column(name = "conversation_id")
    private String conversationID;

    private List<UserProfile> members;

    @OneToMany(mappedBy = "conversations", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Message> messages;

    public Conversation(){
        messages = new ArrayList<>();
    }

    public String getConversationID() {
        return conversationID;
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<UserProfile> getMembers() {
        return members;
    }

    public void setMembers(List<UserProfile> members) {
        this.members = members;
    }

}