package com.lcwd.user.service.UserService.services;

import com.lcwd.user.service.UserService.entities.User;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

import java.util.List;

public interface UserService {

    //user operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user

    User getUser(String userId);


    // Update
    User updateUser(String userId, User user);

    // Delete
    void deleteUser(String userId);
}
