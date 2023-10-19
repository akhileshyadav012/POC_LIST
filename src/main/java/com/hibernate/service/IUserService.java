package com.hibernate.service;

import com.hibernate.entity.User;

import java.util.List;

public interface IUserService {

    User addUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUserById(Long id);
    void updateUserById(Long id, User user);
}
