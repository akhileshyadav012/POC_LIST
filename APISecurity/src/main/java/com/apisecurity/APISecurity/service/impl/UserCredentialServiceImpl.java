package com.apisecurity.APISecurity.service.impl;

import com.apisecurity.APISecurity.entity.UserCredential;
import com.apisecurity.APISecurity.repository.UserCredentialRepository;
import com.apisecurity.APISecurity.request.UserCredentialRequest;
import com.apisecurity.APISecurity.response.UserCredentialDTO;
import com.apisecurity.APISecurity.service.IUserCredentialService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCredentialServiceImpl implements IUserCredentialService {
    private static final Logger logger = LogManager.getLogger(UserCredentialServiceImpl.class);
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserCredentialDTO addUserCredential(UserCredentialRequest request) {
        logger.info("UserCredentialServiceImpl - Inside addUserCredential method");
        UserCredential userCredential = new UserCredential();
        userCredential.setName(request.getName());
        userCredential.setUsername(request.getUsername());
        userCredential.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userCredential.setRole(request.getRole());
        UserCredential saveUserCredential = userCredentialRepository.save(userCredential);
        return userCredential.getUserCredentialDTO();
    }

    public List<UserCredentialDTO> getAllUserCredentials(){
        logger.info("UserCredentialServiceImpl - Inside getAllUserCredentials method");
        List<UserCredential> userCredentialList = userCredentialRepository.findAll();
        List<UserCredentialDTO> userCredentialDTOS = userCredentialList.stream().map(userCredential -> userCredential.getUserCredentialDTO()).collect(Collectors.toList());
        return userCredentialDTOS;
    }
}
