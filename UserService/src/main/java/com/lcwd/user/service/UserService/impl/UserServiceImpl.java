package com.lcwd.user.service.UserService.impl;

import com.lcwd.user.service.UserService.entities.Hotel;
import com.lcwd.user.service.UserService.entities.Rating;
import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.repositories.UserRepository;
import com.lcwd.user.service.UserService.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    //this is where we get the hotel and rating object from userid
    public User getUser(String userId) {

        //fetch user from db
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user with given id is not found in the server:" + userId));


        // fetch rating of the above user from rating service
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);

        logger.info("{} ", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();


        List<Rating> ratingList = ratings.stream().map(rating -> {

            //api call to hotel servoce to get the hotel
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            logger.info("response status code: {}", forEntity.getStatusCode());

            //set the hotel for rating
            rating.setHotel(hotel);
            //return the rating
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;

    }

    @Override
    public User updateUser(String userId, User user) {

        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }
}
