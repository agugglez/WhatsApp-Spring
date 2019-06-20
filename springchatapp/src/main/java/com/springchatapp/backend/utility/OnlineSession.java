package com.springchatapp.backend.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class OnlineSession {
    private Map<Long, String> sessionMap;

    public OnlineSession() {
        this.sessionMap = new HashMap<>();
    }

    public Map<Long, String> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<Long, String> sessionMap) {
        this.sessionMap = sessionMap;
    }
}