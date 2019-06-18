package com.springchatapp.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity

//@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "user_id", updatable = false, insertable = false)
    private long userID;

    //@Column(name = "name")
    private String name;

    //@Column(name = "profile_picture")
    private String avatar;

    //@Column(name = "phone_string")
    private String phoneString;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn//(name = "parent_id")
    private Set<UserProfile> contacts = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneString() {
        return phoneString;
    }

    public void setPhoneString(String phoneString) {
        this.phoneString = phoneString;
    }

    public Set<UserProfile> getContacts() {
        return contacts;
    }

//    public void setContacts(List<UserProfile> contacts) {
//        this.contacts = contacts;
//    }
//
//    public void addConact(UserProfile contact){
//        this.contacts.add(contact);
//    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phoneString='" + phoneString + '\'' +
                ", contacts_count=" + (contacts == null ? 0 : contacts.size()) +
                '}';
    }
}