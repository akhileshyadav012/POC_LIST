package com.user.configuration;

import com.user.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

public class CustomUserDetails implements UserDetails {
    private static final Logger logger = LogManager.getLogger(CustomUserDetails.class);

    private User user;

    public CustomUserDetails(User user){
        this.user = user;
    }

    public User getUser(User user){
        return user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<SimpleGrantedAuthority> set = new HashSet<>();
        set.add(new SimpleGrantedAuthority( String.valueOf(this.user.getRole()) ));
        return set;
    }

    @Override
    public String getPassword() {
        logger.info("CustomUserDetails - Inside getPassword method");
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        logger.info("CustomUserDetails - Inside getUsername method");
        return this.user.getUsername();// change to mobileno
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserId(){
        return user.getUserId();
    }
    public String getRole(){
        return user.getRole();
    }
    public User getUser(){
        return user;
    }
}
