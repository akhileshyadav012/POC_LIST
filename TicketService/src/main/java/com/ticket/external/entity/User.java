package com.ticket.external.entity;

import com.ticket.external.enums.UserRole;
import com.ticket.external.enums.UserStatus;
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
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;
}
