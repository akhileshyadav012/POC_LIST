package com.user.service;

import com.user.entity.User;
import com.user.request.UserRequest;
import com.user.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUserById(String userId);
    List<User> getAllUser();
    void deleteUserById(String userId);
    UserResponse updateUserById(String userId, UserRequest userRequest);
    String demo();
    UserResponse getLoggedInUser(HttpServletRequest request) throws Exception;
}
