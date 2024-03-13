package com.example.askproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Model.Repository.UserRepository;

@Controller
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserEntity entity, Model model) {
        UserEntity userEntity = userRepository.findByUserId(entity.getUserId());

        if (userEntity != null && passwordEncoder.matches(entity.getUserPassword(), userEntity.getUserPassword())) {
            // 사용자가 존재하고, 입력된 비밀번호가 해당 사용자의 비밀번호와 일치하면 로그인이 성공
            return "main"; // 로그인이 성공하면 /main으로 리다이렉트하여 메인 화면으로 이동
        } else {
            return "login"; // 로그인 실패 시 다시 로그인 페이지로 이동

        }
    }

}
