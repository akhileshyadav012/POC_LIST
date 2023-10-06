package com.userservice.service;

import com.userservice.entity.User;

import java.util.List;

public interface IUserService {
    
    User createUser(User user);
    List<User> getAllUser();
    User getUserById(String id);
    User getTemporaryUserById(String id);
    void deleteUserById(String id);
    User updateUserById(String id, User user);
    
}
