package com.example.askproject.Model.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity(name = "UserDto")
@Table(name = "user")
public class UserDTO {
    @Id
    private String userId; // name
    private String userPassword; // pwd
    @Column(unique = true)
    private String userEmail; // email

    private String userRole; // role
    
    private String userNickname;
    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin;

}