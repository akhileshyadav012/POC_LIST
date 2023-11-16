package com.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String mobileNo;
    private String email;
    private String username;
    private String password;
}
