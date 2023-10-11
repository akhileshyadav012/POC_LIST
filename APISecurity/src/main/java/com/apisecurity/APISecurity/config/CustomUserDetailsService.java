package com.apisecurity.APISecurity.config;

import com.apisecurity.APISecurity.entity.UserCredential;
import com.apisecurity.APISecurity.exception.NotFoundException;
import com.apisecurity.APISecurity.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByUsername(username);
        if (userCredential == null){
            throw new NotFoundException("User nhi MIla");
        }
        return new CustomUserDetails(userCredential);
    }
}
