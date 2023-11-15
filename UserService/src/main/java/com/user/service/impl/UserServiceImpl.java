package com.user.service.impl;

import com.user.constants.UserConstants;
import com.user.entity.User;
import com.user.enums.UserRole;
import com.user.enums.UserStatus;
import com.user.exception.NotFoundException;
import com.user.repository.UserRepository;
import com.user.request.UserRequest;
import com.user.response.UserResponse;
import com.user.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest){
        logger.info("UserServiceImpl - Inside createUser method");
        String uuid = String.valueOf(UUID.randomUUID());
        User user = User.builder()
                .userId(uuid)
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .age(userRequest.getAge())
                .mobileNo(userRequest.getMobileNo())
                .email(userRequest.getEmail())
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .build();
        userRepository.save(user);

        UserResponse userResponse = UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .mobileNo(user.getMobileNo())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
        return userResponse;
    }

    public UserResponse getUserById(String userId){
        logger.info("UserServiceImpl - Inside getUserById method");
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()){
            throw new NotFoundException(UserConstants.USER_NOT_FOUND);
        }
        User user = optionalUser.get();
        UserResponse userResponse = UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .mobileNo(user.getMobileNo())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
        return userResponse;
    }

    public List<User> getAllUser(){
        logger.info("UserServiceImpl - Inside getAllUser method");
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public void deleteUserById(String userId){
        logger.info("UserServiceImpl - Inside deleteUserById method");
        userRepository.deleteByUserId(userId);
    }

    public UserResponse updateUserById(String userId, UserRequest userRequest){
        logger.info("UserServiceImpl - Inside deleteUserById method");
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()){
            throw new NotFoundException(UserConstants.USER_NOT_FOUND);
        }
        User user = optionalUser.get();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setAge(userRequest.getAge());
        user.setMobileNo(userRequest.getMobileNo());
        userRepository.save(user);

        UserResponse userResponse = UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .mobileNo(user.getMobileNo())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .build();

        return userResponse;
    }

    public String demo(){
        logger.info("TicketServiceImpl - Inside demoMethod method");
        String number = String.valueOf(100);
        return number;
    }
}
