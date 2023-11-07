package com.user.controller;

import com.user.entity.User;
import com.user.request.UserRequest;
import com.user.response.UserResponse;
import com.user.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        logger.info("UserController - Inside createUser method");
        UserResponse user = userService.createUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "userId") String userId){
        logger.info("UserController - Inside getUserById method");
        UserResponse userResponse = userService.getUserById(userId);
        return ResponseEntity.of(Optional.ofNullable(userResponse));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        logger.info("UserController - Inside getAllUser method");
        List<User> userList = userService.getAllUser();
        return ResponseEntity.of(Optional.ofNullable(userList));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(name = "userId") String userId){
        logger.info("UserController - Inside deleteUserById method");
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable(name = "userId") String userId, @RequestBody UserRequest userRequest){
        logger.info("UserController - Inside updateUserById method");
        UserResponse userResponse = userService.updateUserById(userId, userRequest);
        return ResponseEntity.of(Optional.ofNullable(userResponse));

    }
}
