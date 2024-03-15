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

import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Service.UserService;
import com.example.askproject.Service.UserServiceSecurity;

@Controller
@RequestMapping("/v1")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceSecurity userServiceSecurity;

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
        boolean exists = userService.existsByUserId(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", exists);
        return response;
    }
    /////////////////////////////////////////////


    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO dto, Model model) {
        userServiceSecurity.joinUserDto(dto);
        return "login"; // 로그인 페이지로 이동
    }
}
