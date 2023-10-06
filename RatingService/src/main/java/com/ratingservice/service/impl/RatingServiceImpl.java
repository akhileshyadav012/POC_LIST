package com.ratingservice.service.impl;

import com.ratingservice.entity.Rating;
import com.ratingservice.exceptions.NotFoundException;
import com.ratingservice.repository.RatingRepository;
import com.ratingservice.service.IRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingServiceImpl implements IRatingService {
    private static final Logger logger = LogManager.getLogger(RatingServiceImpl.class);
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        logger.info("RatingServiceImpl - Inside createRating method ");
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        logger.info("RatingServiceImpl - Inside getAllRating method ");
        List<Rating> ratingList = ratingRepository.findAll();
        return ratingList;
    }

    @Override
    public Rating updateRating(String ratingId, Rating rating) {
        logger.info("RatingServiceImpl - Inside updateRating method ");
        Optional<Rating> optionalRating = ratingRepository.findById(ratingId);
        if (optionalRating.isEmpty()){
            throw new NotFoundException("Rating Not Found");
        }
        Rating updateRating = optionalRating.get();
        updateRating.setUserId(rating.getUserId());
        updateRating.setHotelId(rating.getHotelId());
        updateRating.setRating(rating.getRating());
        updateRating.setFeedback(rating.getFeedback());
        return ratingRepository.save(updateRating);
    }

    @Override
    public List<Rating> getRatingByUserID(String id) {
        logger.info("RatingServiceImpl - Inside getRatingByUserID method ");
        List<Rating> ratingByUserId = ratingRepository.getRatingByUserId(id);
        return ratingByUserId;
    }

    @Override
    public List<Rating> getRatingByHotelId(String id) {
        logger.info("RatingServiceImpl - Inside getRatingByHotelId method ");
        List<Rating> ratingByHotelId = ratingRepository.getRatingByHotelId(id);
        return ratingByHotelId;
    }
}
