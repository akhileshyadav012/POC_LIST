package com.user.security;

import com.user.configuration.CustomUserDetailService;
import com.user.configuration.CustomUserDetails;
import com.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTTokenHelper {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    public static final long JWT_TOKEN_VALIDITY = 600000;
//    private String secret = "Ganeshaa";
    private String secret = "wcVoYHql7Bdz416aEkePjTqyRJLZBDSkK8s3EgZCVdDdmOM7beUKNxdI8JpatVnr2amtwIhxHWtk0pB7Vtx0Yluic0n7j3e8gJBoH97wqmro0HGg6iQruvgOqxn45HfV0OCIB86SlxGImejl7zHozN3lovxdh0";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(CustomUserDetails userDetails, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
//        claims.put("userId", user.getUserId());
//        claims.put("firstName", user.getFirstName());
//        claims.put("lastName", user.getLastName());
//        claims.put("age", user.getAge());
//        claims.put("mobileNo", user.getMobileNo());
//        claims.put("email", user.getEmail());
//        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
