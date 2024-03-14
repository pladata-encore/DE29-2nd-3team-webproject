package com.example.askproject.Model.DTO;

import java.time.LocalDateTime;

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
    private String userId;
    private String userPassword;
    private String userEmail;
    private String userRole;
    private String userNickname;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
