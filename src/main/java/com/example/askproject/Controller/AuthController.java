package com.example.askproject.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Service.UserService;
import com.example.askproject.Service.UserServiceSecurity;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceSecurity userServiceSecurity;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "exception", required = false) String exception, Model model) throws Exception{
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/regist";
    }

    // yang => 중복아이디 메시지창 html에서 띄우려고 추가함
    @GetMapping("/check-userid")
    @ResponseBody
    public Map<String, Boolean> checkUserId(@RequestParam String userId) throws Exception{
        boolean exists = userService.existsByUserId(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", exists);
        return response;
    }
    /////////////////////////////////////////////

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO dto, Model model) throws Exception {
        boolean exists = userService.existsByUserId(dto.getUserId());
        if (exists) {
            throw new Exception("계정 이미 있음");
        }
        userServiceSecurity.joinUserDto(dto);
        return "redirect:/login"; // 로그인 페이지로 이동
    }

}
