package com.ratingservice.controller;

import com.ratingservice.entity.Rating;
import com.ratingservice.repository.RatingRepository;
import com.ratingservice.service.IRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ratings")
public class RatingController {
    private static final Logger logger = LogManager.getLogger(RatingController.class);

    @Autowired
    private IRatingService ratingService;

    @PostMapping
    public Rating createRating(@RequestBody Rating rating){
        logger.info("RatingController - Inside createRating method ");
        return ratingService.createRating(rating);
    }
    @GetMapping
    public List<Rating> getAllRatings(){
        logger.info("RatingController - Inside getAllRatings method ");
        return ratingService.getAllRating();
    }
    @PutMapping("/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, @RequestBody Rating rating){
        logger.info("RatingController - Inside updateRating method ");
        return ratingService.updateRating(ratingId, rating);
    }
    @GetMapping("/users/{id}")
    public List<Rating> getRatingByUserID(@PathVariable("id") String id){
        logger.info("RatingController - Inside getRatingByUserID method ");
        return ratingService.getRatingByUserID(id);
    }
    @GetMapping("/hotels/{id}")
    public List<Rating> getRatingByHotelId(@PathVariable("id") String id){
        logger.info("RatingController - Inside getRatingByHotelId method ");
        return ratingService.getRatingByHotelId(id);
    }

}
