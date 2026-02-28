package com.lcwd.user.service.UserService.controller;


import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping({"/","/users"})
public class UserController {

    private final UserService userService;
    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;

    }

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    //single user get
  @GetMapping("/{userId}")
  @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser( @PathVariable String userId){
        User user = userService.getUser(userId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    //creating a fallback method for circuitbreaker

    public ResponseEntity<User>ratingHotelFallback(String userId, Exception ex){

      logger.info("Fallback is executed because service is down: ", ex.getMessage());
      User user = User.builder()
              .email("dummy@gmail.com")
              .name("Dummy")
              .about("The user is created since the service is down")
              .userId("1515")
              .build();
      return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
     List<User>allUser = userService.getAllUser();
     return ResponseEntity.ok(allUser);

    }
}
