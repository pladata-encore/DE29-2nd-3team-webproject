package com.example.askproject.config.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.askproject.Service.UserServiceSecurity;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    @Lazy
    private UserServiceSecurity userServiceSecurity;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info("[LoginAuthSuccessHandler][onAuthenticationSuccess] Start1");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info(userDetails.toString());
        userServiceSecurity.updateIsLoginByName(userDetails.getUsername(), true);
        log.info("[LoginAuthSuccessHandler][onAuthenticationSuccess] Start2");
        response.sendRedirect("/v1/main/index");
        log.info("[LoginAuthSuccessHandler][onAuthenticationSuccess] Start3");
        log.info(userDetails.toString());
        log.info(authentication.toString());
        super.onAuthenticationSuccess(request, response, authentication);
    }

    

}
