package com.springchatapp.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
//@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "conversation_id")
    private long conversationId;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserProfile> members = new HashSet<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Message> messages = new HashSet<>();

    public Conversation(){
//        messages = new ArrayList<>();
    }

    public long getConversationId() {

        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public Set<Message> getMessages() {
        messages.forEach(message -> message.setConversationId(conversationId));
        return messages;
    }

    public Set<UserProfile> getMembers() {
        return members;
    }



//    public void setMembers(List<UserProfile> members) {
//        this.members = members;
//    }

}