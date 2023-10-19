package com.hibernate.controller;

import com.hibernate.entity.User;
import com.hibernate.service.IUserService;
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

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        logger.info("UserController - Inside addUser method");
        try {
            User userAdded = userService.addUser(user);
            return ResponseEntity.of(Optional.ofNullable(userAdded));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/get")
    public ResponseEntity<User> getUserById(@RequestParam(name = "id") Long id){
        logger.info("UserController - Inside getUserById method");
        User userById = userService.getUserById(id);
        if (userById == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(userById);
//            return ResponseEntity.of(Optional.ofNullable(userById));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        logger.info("UserController - Inside getAllUsers method");
        List<User> userList = userService.getAllUsers();
        if (userList.size() <= 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.of(Optional.of(userList));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(name = "id") Long id){
        logger.info("UserController - Inside deleteUserById method");
        try {
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUserById(@PathVariable(name = "id") Long id, @RequestBody User user){
        logger.info("UserController - Inside updateUserById method");
        try {
            userService.updateUserById(id, user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
