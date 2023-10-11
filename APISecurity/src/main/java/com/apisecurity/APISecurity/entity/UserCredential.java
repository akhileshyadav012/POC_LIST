package com.apisecurity.APISecurity.entity;

import com.apisecurity.APISecurity.response.UserCredentialDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String name;
    String username;
    String password;
    String role;

    public UserCredentialDTO getUserCredentialDTO(){
        return new UserCredentialDTO(userId, name, username, role);
    }
}
