package com.user.dto;

import com.user.entity.User;
import com.user.response.UserResponse;

public class UserDTO {

    public static UserResponse convertToDto(User user){
        UserResponse userResponse = UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .mobileNo(user.getMobileNo())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .status(user.getStatus())
                .build();

        return userResponse;
    }
}
