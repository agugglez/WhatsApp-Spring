package com.springchatapp.backend.controllers;

import com.springchatapp.backend.model.UserProfile;
import com.springchatapp.backend.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class UserController {

    private IUserProfileService userProfileService;

    @Autowired
    public UserController(IUserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping(value = "/get/byid")
    public UserProfile getUserById (@RequestParam("userid") String userId){

        long idLong = Long.parseLong(userId);

        return userProfileService.getUserById(idLong).orElse(null);
    }

    @GetMapping(value = "/get/byphone")
    public UserProfile getUserByPhone (@RequestParam("phone") String phone){
        //long idLong = Long.parseLong(phone);

        return userProfileService.getUserByPhone(phone).orElse(null);
    }
}
