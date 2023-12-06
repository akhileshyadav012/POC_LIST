package com.user.controller;

import com.user.configuration.CustomUserDetailService;
import com.user.configuration.CustomUserDetails;
import com.user.entity.AccessToken;
import com.user.entity.User;
import com.user.exception.NotFoundException;
import com.user.repository.AccessTokenRepository;
import com.user.request.JWTAuthRequest;
import com.user.response.JWTAuthResponse;
import com.user.security.JWTTokenHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private static final Logger logger = LogManager.getLogger(AuthController.class);
    private String secret = "wcVoYHql7Bdz416aEkePjTqyRJLZBDSkK8s3EgZCVdDdmOM7beUKNxdI8JpatVnr2amtwIhxHWtk0pB7Vtx0Yluic0n7j3e8gJBoH97wqmro0HGg6iQruvgOqxn45HfV0OCIB86SlxGImejl7zHozN3lovxdh0";

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
        Authentication authentication = authenticate(request.getUsername(), request.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String generatedToken = jwtTokenHelper.generateToken(userDetails);

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(generatedToken).getBody();
        String role = claims.get("role", String.class);
        System.out.println("user role  :" + role);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        AccessToken accessToken = new AccessToken();
        accessToken.setToken(generatedToken);
        accessTokenRepository.save(accessToken);

        JWTAuthResponse response = new JWTAuthResponse();
        response.setToken(generatedToken);
        return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return authentication;
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

    @GetMapping("/validate")
    public Boolean validateToken(@RequestParam(name = "token") String token){
        logger.info("AuthController - Inside validateToken method");
        String usernameFromToken = jwtTokenHelper.getUsernameFromToken(token);
        System.out.println("username = " + usernameFromToken);
        UserDetails userDetails = customUserDetailService.loadUserByUsername(usernameFromToken);
        Boolean validatedToken = jwtTokenHelper.validateToken(token, userDetails);
        return validatedToken;
    }

}
