package com.ticket.external.response;

import com.ticket.external.enums.UserRole;
import com.ticket.external.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private int age;
    private String mobileNo;
    private String email;
    private UserRole role;
    private UserStatus status;
}
