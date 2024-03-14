package com.example.askproject.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId; // name
    private String userPassword; // pwd
    private String userEmail; // email

    private String userRole; // role
    
    private String userNickname;

    private Boolean isLogin;

}