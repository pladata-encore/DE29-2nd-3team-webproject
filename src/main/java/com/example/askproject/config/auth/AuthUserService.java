package com.example.askproject.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Model.Repository.UserRepository;

@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setIsLogin(userEntity.getIsLogin());
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUserNickname(userEntity.getUserNickname());
        userDTO.setUserPassword(userEntity.getUserPassword());
        userDTO.setUserRole(userEntity.getUserRole());

        return new AuthUserDto(userDTO);
    }

}
