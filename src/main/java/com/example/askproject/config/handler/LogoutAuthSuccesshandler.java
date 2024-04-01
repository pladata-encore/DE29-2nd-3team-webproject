package com.example.askproject.config.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.askproject.Exception.UserException;
import com.example.askproject.Service.UserService;
import com.example.askproject.Service.UserServiceSecurity;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogoutAuthSuccesshandler implements LogoutSuccessHandler {

    @Autowired
    @Lazy
    private UserServiceSecurity userServiceSecurity;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        try {
            userServiceSecurity.updateIsLoginByName(userDetails.getUsername(), false);
        }
        catch (Exception e){
        }

        response.sendRedirect("/");

    }

}
