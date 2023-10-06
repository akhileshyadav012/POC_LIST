package com.userservice.security;

import com.userservice.controller.AuthController;
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

//4th Step: is use taab hai jaab aapan token daal ke koi api access karte hai taab and ye check karta hai ki jo token daala hai oosme ka
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LogManager.getLogger(AuthController.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("JWTAuthenticationFilter - Inside doFilterInternal method");
//        1) get jwt token from header i.e request mai se token get karna hai
        String requestToken = request.getHeader("Authorization");
        System.out.println("requestToken = "+ requestToken);
        String username = null;
        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            System.out.println("token = " + token);
            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
                System.out.println("username = " + username);
                logger.info("username = " + username);
            }catch (Exception e){
                System.out.println(e);
            }
        }else {
            System.out.println("Jwt Token doesnot begin with Bearer !!!");
        }

//        2) once we got token, now we will validate token, and set Authentication in SecurityContextHolder:
         if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){

             UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
             if (jwtTokenHelper.validateToken(token,userDetails )){
//                sahi chal raha hai
//                token ko authenticate karna hai aab
                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
             }else{
                 System.out.println("Invalid JWT Token");
             }

         }else{
             System.out.println("Username is null or context is not null");
         }

//         3) once all is set now let filterChain do internal security work
         filterChain.doFilter(request, response);

    }
}
