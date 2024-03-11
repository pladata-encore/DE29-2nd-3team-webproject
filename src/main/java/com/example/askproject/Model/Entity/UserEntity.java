package com.example.askproject.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class UserEntity extends BaseTimeEntity{
    @Id
    @NotBlank
    private String userId;
    @NotBlank
    private String userPassword;
    @Email
    private String userEmail;
    private String userRole;
    private String userNickname;
}
