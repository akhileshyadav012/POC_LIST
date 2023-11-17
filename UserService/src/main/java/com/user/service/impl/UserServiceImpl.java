package com.user.service.impl;

import com.user.configuration.CustomUserDetailService;
import com.user.constants.UserConstants;
import com.user.dto.UserDTO;
import com.user.entity.User;
import com.user.enums.UserRole;
import com.user.enums.UserStatus;
import com.user.exception.NotFoundException;
import com.user.repository.UserRepository;
import com.user.request.UserRequest;
import com.user.response.UserResponse;
import com.user.security.JWTTokenHelper;
import com.user.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;

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
                .username(userRequest.getUsername())
                .password(this.bCryptPasswordEncoder.encode(userRequest.getPassword()))
                .role(String.valueOf(UserRole.ROLE_USER))
                .status(String.valueOf(UserStatus.ACTIVE))
                .build();
        userRepository.save(user);

        return UserDTO.convertToDto(user);
    }

    public UserResponse getUserById(String userId){
        logger.info("UserServiceImpl - Inside getUserById method");
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()){
            throw new NotFoundException(UserConstants.USER_NOT_FOUND);
        }
        User user = optionalUser.get();
        return UserDTO.convertToDto(user);
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
        user.setUsername(userRequest.getUsername());
        user.setPassword(this.bCryptPasswordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);

        return UserDTO.convertToDto(user);
    }

    public String demo(){
        logger.info("TicketServiceImpl - Inside demoMethod method");
        String number = String.valueOf(100);
        return number;
    }

    public UserResponse getLoggedInUser(HttpServletRequest request) throws Exception {
        logger.info("UserServiceImpl - Inside getLoggedInUser method");

//        String username = CommonUtil.getLoggedinUser().getUsername();
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println("commonutils = " + principal.toString());

        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        System.out.println("token of logged in user = " + token);
        String usernameFromToken = jwtTokenHelper.getUsernameFromToken(token);
        Optional<User> optionalUser = userRepository.findByUsername(usernameFromToken);
        if (optionalUser.isEmpty()){
            throw new NotFoundException("User nhi mila");
        }
        User user = optionalUser.get();
        return UserDTO.convertToDto(user);
    }

}
