package com.example.askproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Model.Repository.UserRepository;

@RestController
@RequestMapping("/api")
public class LoginRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String userPassword) { // 로그인할때 userId과 password 받음
        UserEntity userEntity = userRepository.findByUserId(userId); // 사용자 이름에 해당하는 userentity를 db에서 찾음

        if (userEntity != null && userEntity.getUserPassword().equals(userPassword)) {
            return "Login successful";
        } else {
            return "Invalid userId or password";
        }
    }
    // 사용자가 존재하고 입력된 비밀번호와 일치할 경우 "Login successful"을 반환. 그렇지 않으면 "Invalid
    // username or password"를 반환

    @PostMapping("/register")
    public String register(@RequestParam String userId, @RequestParam String userPassword,
            @RequestParam String userEmail, @RequestParam String userNickname) {
        // 중복된 userId가 있는지 확인
        if (userRepository.existsByUserId(userId)) {
            return "User ID already exists";
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(userPassword);

        // 관리자인지 확인하여 권한 설정
        boolean isAdmin = userId.equalsIgnoreCase("admin");

        // 새로운 사용자 엔터티 생성
        UserEntity newUser = new UserEntity();
        newUser.setUserId(userId);
        newUser.setUserPassword(encryptedPassword);
        newUser.setUserEmail(userEmail);
        newUser.setUserNickname(userNickname);
        newUser.setAdmin(isAdmin);

        // 사용자 저장
        userRepository.save(newUser);

        return "User registered successfully";
    }

}
