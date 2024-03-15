package com.example.askproject.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Model.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity != null) {
            log.info("[LoadUserByUsername] [get AuthUserDTO]");
            return new AuthUserDto(userEntity);
        }

        return null;
    }

}
