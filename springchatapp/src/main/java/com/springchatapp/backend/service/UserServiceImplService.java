package com.springchatapp.backend.service;

import com.springchatapp.backend.model.UserProfile;
import com.springchatapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
