package com.ratingservice.service;

import com.ratingservice.entity.Rating;

import java.util.List;

public interface IRatingService {
    Rating createRating(Rating rating);
    List<Rating> getAllRating();
    Rating updateRating(String ratingId, Rating rating);

    List<Rating> getRatingByUserID(String id);
    List<Rating> getRatingByHotelId(String id);
}
