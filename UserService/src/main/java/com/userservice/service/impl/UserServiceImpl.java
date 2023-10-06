package com.userservice.service.impl;

import com.userservice.entity.Hotel;
import com.userservice.entity.Rating;
import com.userservice.entity.User;
import com.userservice.exceptions.NotFoundException;
import com.userservice.feignclient.IHotelService;
import com.userservice.feignclient.IRatingService;
import com.userservice.repository.UserRepository;
import com.userservice.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IHotelService hotelService;
    @Autowired
    private IRatingService ratingService;

    @Override
    public User createUser(User user) {
        logger.info("UserServiceImpl - Inside createUser method ");
        String randomUUID = UUID.randomUUID().toString();
        user.setUserId(randomUUID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        logger.info("UserServiceImpl - Inside getAllUser method ");
        List<User> userList = userRepository.findAll();
        List<String> userIdList = userList.stream().map(user -> user.getUserId()).collect(Collectors.toList());
        for (String userId : userIdList){
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()){
                throw new NotFoundException("USER NOT FOUND");
            }
            User user = optionalUser.get();
            Rating[] ratingArray = restTemplate.getForObject("http://RATING-SERVICE/v1/ratings/users/" + userId, Rating[].class);
            List<Rating> ratingList = Arrays.asList(ratingArray);
            List<Rating> ratings = ratingList.stream().map(rating -> {
                Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/v1/hotels/" + rating.getHotelId(), Hotel.class);
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());

            user.setRatings(ratings);
        }
        return userList;
    }

    @Override
    public User getUserById(String id) {
        logger.info("UserServiceImpl - Inside getUserById method ");
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new NotFoundException("User Not Found");
        }
        User user = optionalUser.get();
//        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/v1/ratings/users/" + user.getUserId(), Rating[].class);
        List<Rating> userRatings = ratingService.getRatings(user.getUserId());
//        List<Rating> userRatings = Arrays.asList(ratings);

        List<Rating> ratingList = userRatings.stream().map(rating -> {
//            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/v1/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User getTemporaryUserById(String id) {
        logger.info("UserServiceImpl - Inside getTemporaryUserById method ");
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new NotFoundException("User Not Found");
        }
        User user = optionalUser.get();
        return user;
    }

    @Override
    public void deleteUserById(String id) {
        logger.info("UserServiceImpl - Inside deleteUserById method ");
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(String id, User user) {
        logger.info("UserServiceImpl - Inside updateUserById method ");
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new NotFoundException("User Not Found");
        }
        User updateUser = optionalUser.get();
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setAbout(user.getAbout());
        updateUser.setContact(user.getContact());
        return userRepository.save(updateUser);
    }
}
