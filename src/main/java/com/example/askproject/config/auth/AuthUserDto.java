package com.example.askproject.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.askproject.Model.Entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j

@AllArgsConstructor
public class AuthUserDto implements UserDetails{
    private UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("[AuthUserDTO] [GetAuthorities]");
        // TODO Auto-generated method stub
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                log.info(userEntity.toString());
                return userEntity.getUserRole();
            }
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return userEntity.getUserPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userEntity.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    
    
}
