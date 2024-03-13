package com.example.askproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Model.Repository.UserRepository;

@Service
public class UserServiceSecurity {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void updateIsLoginByName(String name, Boolean isLogin) {
        UserDTO dto = userRepository.getUserDtoByName(name);
        UserEntity entity = new UserEntity();
        entity.setUserEmail(dto.getUserEmail());
        entity.setUserNickname(dto.getUserNickname());
        entity.setUserPassword(dto.getUserPassword());
        entity.setUserRole(dto.getUserRole());
        entity.setUserId(dto.getUserId());
        dto.setIsLogin(isLogin);
        userRepository.save(entity);
    }

    public void joinUserDto(UserDTO dto) {
        dto.setUserRole("USER");
        if (dto.getUserId().equals("admin")) {
            dto.setUserRole("ADMIN");
        } else if (dto.getUserId().equals("manager")) {
            dto.setUserRole("MANAGER");
        }

        String rawPwd = dto.getUserPassword();
        String encodedPwd = bCryptPasswordEncoder.encode(rawPwd);
        dto.setUserPassword(encodedPwd);

        dto.setIsLogin(false);
        UserEntity entity = new UserEntity();
        entity.setUserEmail(dto.getUserEmail());
        entity.setUserNickname(dto.getUserNickname());
        entity.setUserPassword(dto.getUserPassword());
        entity.setUserRole(dto.getUserRole());
        entity.setUserId(dto.getUserId());

        userRepository.save(entity);
    }
}
