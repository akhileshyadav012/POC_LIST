package com.apisecurity.APISecurity.controller;

import com.apisecurity.APISecurity.request.UserCredentialRequest;
import com.apisecurity.APISecurity.response.UserCredentialDTO;
import com.apisecurity.APISecurity.service.impl.UserCredentialServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user-credential")
public class UserCredentialController {
    private static final Logger logger = LogManager.getLogger(UserCredentialController.class);
    @Autowired
    private UserCredentialServiceImpl userCredentialService;

    @PostMapping
    public UserCredentialDTO addUserCredential(@RequestBody UserCredentialRequest request) {
        logger.info("UserCredentialController - Inside addUserCredential method");
        return userCredentialService.addUserCredential(request);
    }

    @GetMapping
    public List<UserCredentialDTO> getAllUserCredentials() {
        logger.info("UserCredentialController - Inside getAllUserCredentials method");
        return userCredentialService.getAllUserCredentials();
    }

}
