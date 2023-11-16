package com.user.entity;

import com.user.enums.UserRole;
import com.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;
    private String firstName;
    private String lastName;
    private int age;
    private String mobileNo;
    private String email;
    private String username;
    private String password;
    private String role = String.valueOf(UserRole.ROLE_USER);
    private String status = String.valueOf(UserStatus.ACTIVE);
}