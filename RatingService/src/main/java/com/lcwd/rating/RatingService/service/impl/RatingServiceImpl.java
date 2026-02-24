package com.lcwd.rating.RatingService.service.impl;

import com.lcwd.rating.RatingService.entities.Rating;
import com.lcwd.rating.RatingService.repository.RatingRepository;
import com.lcwd.rating.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        String ratingId= UUID.randomUUID().toString();
         rating.setRatingId(ratingId);

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {

        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {

        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {

        return ratingRepository.findByHotelId(hotelId);
    }


}

