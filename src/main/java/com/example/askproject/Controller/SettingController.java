package com.example.askproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.askproject.Model.DTO.MessageDTO;
import com.example.askproject.Model.DTO.PageDTO;
import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Service.PageService;
import com.example.askproject.Service.UserService;
import com.example.askproject.Service.UserServiceSecurity;

@RequestMapping("/v1/setting")
@Controller
public class SettingController {
    @Autowired
    private UserService userService;
    @Autowired
    private PageService pageService;
    @Autowired
    private UserServiceSecurity userServiceSecurity;


    @GetMapping("/page")
    public String getPageSetting(Model settingModel, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        settingModel.addAttribute("Nickname", userService.findNicknameByUserId(userDetails.getUsername()));
        settingModel.addAttribute("myId", userDetails.getUsername());
        PageDTO pagedto = pageService.findByPageId(userDetails.getUsername());
        settingModel.addAttribute("pageDTO", pagedto);
        UserDTO userdto = userService.findByUserId(userDetails.getUsername());
        settingModel.addAttribute("userDTO", userdto);
        return "pagesetting";
    }

    @PostMapping("/pageupdate")
    public String updatePageSetting(@ModelAttribute PageDTO pageDTO, Authentication authentication, Model model) {
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        pageDTO.setPageId(userDetails.getUsername()); // 세션id가 될 값입니다.
        pageService.updatePage(pageDTO);
        MessageDTO message = new MessageDTO("페이지 정보가 업데이트 되었습니다.", "/v1/main", RequestMethod.GET);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/updatepassword")
    public String updatePassword(@ModelAttribute UserDTO userDTO, Authentication authentication, Model model) {
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDTO.setUserId(userDetails.getUsername()); // 세션id가 될 값입니다.
        userServiceSecurity.changePassword(userDTO);
        MessageDTO message = new MessageDTO("비밀번호 수정이 완료되었습니다.", "/v1/main", RequestMethod.GET);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/updatenickname")
    public String updateNickname(@ModelAttribute UserDTO userDTO, Authentication authentication, Model model) {
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDTO.setUserId(userDetails.getUsername()); // 세션id가 될 값입니다.
        userService.updateUser(userDTO);
        MessageDTO message = new MessageDTO("닉네임이 수정이 완료되었습니다.", "/v1/main", RequestMethod.GET);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/deleteuser")
    public String deleteUser(Authentication authentication) {
        //TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userService.deleteUser(userDetails.getUsername());
        pageService.deletePage(userDetails.getUsername());
        return "redirect:/logout";
    }
    


    private String showMessageAndRedirect(final MessageDTO params, Model model) {
        model.addAttribute("params", params);
        return "messageRedirect";
    }

    @PostMapping("/verify-password")
    @ResponseBody
    public boolean verifyPassword(@RequestParam String currentPassword, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userServiceSecurity.verifyPassword(userDetails.getUsername(), currentPassword);
    }
}
