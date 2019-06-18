package com.springchatapp.backend.model;

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

    @ManyToMany
    private Set<UserProfile> members = new HashSet<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL , orphanRemoval = true)
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
        return messages;
    }

    public Set<UserProfile> getMembers() {
        return members;
    }

//    public void setMembers(List<UserProfile> members) {
//        this.members = members;
//    }

}