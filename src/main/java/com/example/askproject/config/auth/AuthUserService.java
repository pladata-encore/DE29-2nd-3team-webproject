package com.example.askproject.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Model.Repository.UserRepository;

@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        UserDTO userDTO = userRepository.getUserDtoByName(name);

        if (userDTO != null) {
            return new AuthUserDto(userDTO);
        }
        return null;
    }

}
