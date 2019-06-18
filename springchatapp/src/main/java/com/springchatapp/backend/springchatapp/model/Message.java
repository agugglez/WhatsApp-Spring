package com.springchatapp.backend.springchatapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "message")
@Table(name = "messages")
public class Message{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int messageID;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private UserProfile from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Column(name = "txt")
    private String txt;

    public UserProfile getFrom() {
        return from;
    }

    public void setFrom(UserProfile from) {
        this.from = from;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversationID(Conversation conversationID) {
        this.conversation = conversationID;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    
}