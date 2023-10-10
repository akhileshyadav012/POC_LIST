package com.userservice.controller;

import com.userservice.entity.User;
import com.userservice.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<User> getAllUser(){
        logger.info("UserController - Inside getAllUser method");
        return userService.getAllUser();

    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id){
        logger.info("UserController - Inside getUserById method");
        return userService.getUserById(id);
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
