package com.user.controller;

import com.user.configuration.CustomUserDetailService;
import com.user.entity.AccessToken;
import com.user.exception.NotFoundException;
import com.user.repository.AccessTokenRepository;
import com.user.request.JWTAuthRequest;
import com.user.response.JWTAuthResponse;
import com.user.security.JWTTokenHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private static final Logger logger = LogManager.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody JWTAuthRequest request) {
        logger.info("AuthController - Inside login method");
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getUsername());
        String generatedToken = jwtTokenHelper.generateToken(userDetails);
        System.out.println("generatedToken = " + generatedToken);

        AccessToken accessToken = new AccessToken();
        accessToken.setToken(generatedToken);
        accessTokenRepository.save(accessToken);

        JWTAuthResponse response = new JWTAuthResponse();
        response.setToken(generatedToken);
        return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            System.out.println(e);
            throw new NotFoundException("Invalid Username or Password!!!");
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        logger.info("AuthController - Inside logout method");
        String token = null;
        String headerToken = request.getHeader("Authorization");
        token = headerToken.substring(7);
        logger.info("token at logout = " + token);
        Optional<AccessToken> optionalAccessToken = accessTokenRepository.findByTokenAndActiveToken(token, Boolean.TRUE);
        if (optionalAccessToken.isEmpty()) {
            throw new NotFoundException("Token nhi mila!!!");
        }
        AccessToken accessToken = optionalAccessToken.get();
        accessToken.setActiveToken(Boolean.FALSE);
        accessToken.setLogoutAt(LocalDateTime.now());
        accessTokenRepository.save(accessToken);
    }

}
