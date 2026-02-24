package com.lcwd.user.service.UserService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Rating {

 private String ratingId;
 private String userId;
 private String hotelId;
 private int rating;
 private String feedback;

 private Hotel hotel;

}