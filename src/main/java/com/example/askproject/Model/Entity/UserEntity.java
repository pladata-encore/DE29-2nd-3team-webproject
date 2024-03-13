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



    
     // 'findAllUserId' 속성 추가
     private String findAllUserId;

     // Getter 및 Setter 추가 (필요시)
     public String getFindAllUserId() {
         return findAllUserId;
     }
 
     public void setFindAllUserId(String findAllUserId) {
         this.findAllUserId = findAllUserId;
     }


      private boolean isAdmin;

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
