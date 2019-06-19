package com.springchatapp.backend.service;

import com.springchatapp.backend.model.UserProfile;

import java.util.List;
import java.util.Optional;

public interface IUserProfileService {

    List<UserProfile> getAll();

    UserProfile save(UserProfile userProfile);

    Optional<UserProfile> getUserById(long userId);

    Optional<UserProfile> getUserByPhone(String phone);
}
