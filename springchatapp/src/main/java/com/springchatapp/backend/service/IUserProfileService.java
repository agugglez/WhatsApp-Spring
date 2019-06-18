package com.springchatapp.backend.service;

import com.springchatapp.backend.model.UserProfile;

import java.util.List;

public interface IUserProfileService {

    List<UserProfile> getAll();

    UserProfile save(UserProfile userProfile);
}
