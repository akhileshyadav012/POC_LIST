package com.user.utils;

import com.user.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class CommonUtil {

    public static User getLoggedinUser() throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (principal instanceof User) {
            String username = ((User) principal).getUsername();
        } else {
            throw new Exception("Unauthorized User");
        }
        return (User) principal;
    }

}