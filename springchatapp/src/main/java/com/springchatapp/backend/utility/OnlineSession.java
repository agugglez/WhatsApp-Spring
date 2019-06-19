package com.springchatapp.backend.utility;

import java.util.Map;

import com.springchatapp.backend.model.UserProfile;

public class OnlineSession{
    private Map<UserProfile , String> session;

    public Map<UserProfile, String> getSession() {
        return session;
    }

    public void setSession(Map<UserProfile, String> session) {
        this.session = session;
    }
}