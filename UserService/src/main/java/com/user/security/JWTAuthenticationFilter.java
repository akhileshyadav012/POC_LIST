package com.user.security;

import com.user.configuration.CustomUserDetailService;
import com.user.entity.AccessToken;
import com.user.repository.AccessTokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LogManager.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("JWTAuthenticationFilter - Inside doFilterInternal class");
        String headerToken = request.getHeader("Authorization");
        logger.info("headerToken = " + headerToken);
        String username = null;
        String token = null;

        if (headerToken != null && headerToken.startsWith("Bearer")){
            token = headerToken.substring(7);
            logger.info("token = " + token);
            try{
                username = jwtTokenHelper.getUsernameFromToken(token);
                logger.info("username = " + username);
            }catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }else {
            System.out.println("Jwt Token doesnot begin with Bearer !!!");
        }

        Optional<AccessToken> optionalAccessToken = accessTokenRepository.findByTokenAndActiveToken(token, Boolean.TRUE);
        System.out.println("expired or not = " + optionalAccessToken);
        if (optionalAccessToken.isPresent()){
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);
                if (jwtTokenHelper.validateToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }else {
                    System.out.println("Access Denied because of wrong JWT Token!!!");
                }
            }else {
                System.out.println("Username is null OR Authentication is not null");
            }
        }
        filterChain.doFilter(request, response);
    }
}
