package com.apisecurity.APISecurity.controller;

import com.apisecurity.APISecurity.exception.NotFoundException;
import com.apisecurity.APISecurity.request.JWTAuthRequest;
import com.apisecurity.APISecurity.response.JWTAuthResponse;
import com.apisecurity.APISecurity.security.JWTTokenHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private static final Logger logger = LogManager.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody JWTAuthRequest request){
        logger.info("AuthController - Inside login method");
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtTokenHelper.generateToken(userDetails);

        JWTAuthResponse response = new JWTAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(username, password);
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception e){
            System.out.println(e);
            throw new NotFoundException("Invalid Username or Password!!!");
        }
    }



}
