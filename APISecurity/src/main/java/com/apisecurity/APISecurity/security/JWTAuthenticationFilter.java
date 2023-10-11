package com.apisecurity.APISecurity.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LogManager.getLogger(JWTAuthenticationFilter.class);
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;

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
            logger.info("expiration date = " + jwtTokenHelper.getExpirationDateFromToken(token));
            try {
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

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
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

        filterChain.doFilter(request, response);
    }
}
