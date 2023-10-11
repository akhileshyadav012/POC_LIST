package com.apisecurity.APISecurity.service;

import com.apisecurity.APISecurity.request.UserCredentialRequest;
import com.apisecurity.APISecurity.response.UserCredentialDTO;

import java.util.List;

public interface IUserCredentialService {
    UserCredentialDTO addUserCredential(UserCredentialRequest request);
    List<UserCredentialDTO> getAllUserCredentials();
}
