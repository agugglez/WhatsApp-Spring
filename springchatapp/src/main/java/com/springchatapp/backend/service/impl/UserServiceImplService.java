package com.springchatapp.backend.service.impl;

import com.springchatapp.backend.model.UserProfile;
import com.springchatapp.backend.repository.UserRepository;
import com.springchatapp.backend.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplService implements IUserProfileService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImplService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserProfile> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        return userRepository.save(userProfile);
    }

    @Override
    public Optional<UserProfile> getUserById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<UserProfile> getUserByPhone(String phone) {
        return userRepository.findByPhoneString(phone);
    }
}
