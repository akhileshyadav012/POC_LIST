package com.userservice.service.impl;

import com.userservice.entity.User;
import com.userservice.exceptions.NotFoundException;
import com.userservice.repository.UserRepository;
import com.userservice.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        logger.info("UserServiceImpl - Inside createUser method ");
        String randomUUID = UUID.randomUUID().toString();
        user.setUserId(randomUUID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser(int pageNumber, int pageSize) {
        logger.info("UserServiceImpl - Inside getAllUser method ");

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> page = userRepository.findAll(pageable);
        List<User> userList = page.getContent();

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
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setRole(user.getRole());
        return userRepository.save(updateUser);
    }
}
