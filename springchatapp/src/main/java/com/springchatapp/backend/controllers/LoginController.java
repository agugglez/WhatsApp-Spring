package com.springchatapp.backend.controllers;

import com.springchatapp.backend.model.LoginResultModel;
import com.springchatapp.backend.model.UserProfile;
import com.springchatapp.backend.service.IUserProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {
    private IUserProfileService userProfileService;

    @Autowired
    public LoginController(IUserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping(value = "/login")
    public LoginResultModel login(@RequestParam("phone") String phone , @RequestParam("password") String password) {
        LoginResultModel token = new LoginResultModel();  
        
        UserProfile profile = userProfileService.getUserByPhone(phone).orElse(null); 
        if(profile != null)
            token.setToken(profile.getPhoneString());
        else token.setError("login failed: error mesage");
        return token;
    }
}