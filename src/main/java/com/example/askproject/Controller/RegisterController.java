package com.example.askproject.Controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Model.Repository.UserRepository;

@Controller
@RequestMapping("/v1")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "regist";
    }

    //yang => 중복아이디 메시지창 html에서 띄우려고 추가함
    @GetMapping("/check-userid")
    @ResponseBody
    public Map<String, Boolean> checkUserId(@RequestParam String userId) {
        boolean exists = userRepository.existsByUserId(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", exists);
        return response;
    }
    /////////////////////////////////////////////


    @PostMapping("/register")
    public String register(@ModelAttribute UserEntity entity, Model model) {

        // 다른 입력칸이 비어있는데도 존재하지 않는 아이디를 아이디칸에 입력하면 로그인 페이지로 넘어가서 추가함
        //yang
        // 입력값이 비어있는지 확인
        if (entity.getUserId().isEmpty() || entity.getUserPassword().isEmpty() || entity.getUserEmail().isEmpty()) {
            model.addAttribute("message", "Please fill out all fields");
            return "regist";
        }

        // 이미 존재하는 아이디인지 확인
        if (userRepository.existsByUserId(entity.getUserId())) {
            model.addAttribute("message", "User ID already exists");
            return "regist";
        }

        // 이미 존재하는 이메일인지 확인
        if (userRepository.existsByUserEmail(entity.getUserEmail())) {
            model.addAttribute("message", "Email already exists");
            return "regist";
        }

        // 이미 존재하는 비밀번호인지 확인
        if (userRepository.existsByUserPassword(passwordEncoder.encode(entity.getUserPassword()))) {
        model.addAttribute("message", "Please use a different password");
        return "regist";
    }


        // 비밀번호를 해시화하여 저장
        String encryptedPassword = passwordEncoder.encode(entity.getUserPassword()); 
        entity.setUserPassword(encryptedPassword);

        boolean isAdmin = entity.getUserId().equalsIgnoreCase("admin"); // 입력된 아이디가 "admin"인지 확인하여 관리자 여부를 설정

        UserEntity newUser = new UserEntity();
        newUser.setUserId(entity.getUserId());
        newUser.setUserPassword(encryptedPassword);
        newUser.setUserEmail(entity.getUserEmail());
        newUser.setUserNickname(entity.getUserNickname());
        newUser.setAdmin(isAdmin);

        userRepository.save(newUser); // 새로운 사용자 정보를 데이터베이스에 저장

        model.addAttribute("message", "User registered successfully"); // 성공 메시지를 모델에 추가
        return "login"; // 로그인 페이지로 이동
    }
}
