package com.user.configuration;

import com.user.entity.User;
import com.user.exception.NotFoundException;
import com.user.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(CustomUserDetailService.class);
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("CustomUserDetailService - Inside loadUserByUsername method ");
        System.out.println("username = " + username);
        Optional<User> optionalUser = this.userRepository.findByUsername(username);
        if (optionalUser.isEmpty()){// change to mobile no:
            throw new NotFoundException("User with this username is not Found");
        }
        User user = optionalUser.get();
        return new CustomUserDetails(user);
    }

}
