package com.lcwd.rating.RatingService.service;

import com.lcwd.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create

    Rating create (Rating rating);


    //get all

    List<Rating> getRatings();


    //get single
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel

    List<Rating>getRatingByHotelId(String hotelid);
}

