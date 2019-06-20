package com.springchatapp.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Arrays;

@Entity(name = "message")
//@Table(name = "messages")
public class Message {

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    private long conversationId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private long messageID;

    @OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id")
    private UserProfile from;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "conversation_id")
    @JsonBackReference
    private Conversation conversation;

    @Column(name = "txt")
    private String txt;

    public long getMessageID() {
        return messageID;
    }

    public void setMessageID(long messageID) {
        this.messageID = messageID;
    }

    public UserProfile getFrom() {
        return from;
    }

    public void setFrom(UserProfile from) {
        this.from = from;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {

        this.conversation = conversation;

    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }


    @Override
    public String toString() {
        return "Message{" +
                "conversationId=" + conversationId +
                ", messageID=" + messageID +
                ", from=" + from +
                ", txt='" + txt + '\'' +
                '}';
    }
}