package com.userservice.controller;

import com.userservice.entity.User;
import com.userservice.service.IUserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        logger.info("UserController - Inside createUser method");
        return userService.createUser(user);
    }

    @GetMapping
    @CircuitBreaker(name = "getAllUserCircuitBreaker", fallbackMethod = "getAllUserFallbackService")
    public List<User> getAllUser(){
        logger.info("UserController - Inside getAllUser method");
        return userService.getAllUser();

    }

    public List<User> getAllUserFallbackService(Exception ex){
        logger.info("UserController - Inside getAllUserFallbackService method");
        logger.info("This method is executed because getAllUserCircuitBreaker method is executed!!!", ex.getMessage());
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserId("12345");
        user.setName("DummyNameList");
        user.setEmail("dummynamelist@gmail.com");
        user.setContact("dummy12345678");
        user.setAbout("This user is created dummyList because some service may be down.");

        users.add(user);

        return users;

    }

//    int retryCount = 1;
    @GetMapping("/{id}")
    @CircuitBreaker(name = "ratingHotelCircuitBreaker", fallbackMethod = "ratingHotelFallbackService")
//    @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallbackService")
//    @RateLimiter(name = "getUserByIdRateLimiter", fallbackMethod = "ratingHotelFallbackService")
    public User getUserById(@PathVariable("id") String id){
        logger.info("UserController - Inside getUserById method");
//        logger.info("retryCOUNT = " + retryCount);
//        retryCount++;
        return userService.getUserById(id);
    }

    public User ratingHotelFallbackService(String id, Exception ex){
        logger.info("UserController - Inside ratingHotelFallbackService method");
        logger.info("This method is executed because ratingHotelCircuitBreaker method is executed!!!", ex.getMessage());
        User user = new User();
        user.setUserId("12345");
        user.setName("DummyName");
        user.setEmail("dummy@gmail.com");
        user.setContact("dummy12345678");
        user.setAbout("This user is created dummy because some service may be down.");
        return user;
    }

    @GetMapping("/id/{id}")
    public User getTemporaryUserById(@PathVariable("id") String id){
        logger.info("UserController - Inside getTemporaryUserById method");
        return userService.getTemporaryUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") String id){
        logger.info("UserController - Inside deleteUserById method");
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable("id") String id, @RequestBody User user){
        logger.info("UserController - Inside updateUserById method");
        return userService.updateUserById(id, user);
    }

}
