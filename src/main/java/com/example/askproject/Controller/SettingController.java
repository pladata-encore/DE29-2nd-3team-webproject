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

import com.example.askproject.Model.DTO.PageDTO;
import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Service.PageService;
import com.example.askproject.Service.UserService;

@RequestMapping("/v1/setting")
@Controller
public class SettingController {
    @Autowired
    private UserService userService;
    @Autowired
    private PageService pageService;

    @GetMapping("/user")
    public String getUserSetting(Model settingModel, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        settingModel.addAttribute("myId", userDetails.getUsername());
        UserDTO userdto = userService.findByUserId(userDetails.getUsername());
        settingModel.addAttribute("userdto", userdto);
        return "myaccount";
    }

    @GetMapping("/page")
    public String getPageSetting(Model settingModel, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        settingModel.addAttribute("myId", userDetails.getUsername());
        PageDTO pagedto = pageService.findByPageId(userDetails.getUsername());
        settingModel.addAttribute("pageDTO", pagedto);
        UserDTO userdto = userService.findByUserId(userDetails.getUsername());
        settingModel.addAttribute("userdto", userdto);
        return "pagesetting";
    }

    @PostMapping("/pageupdate")
    public String updatePageSetting(@ModelAttribute PageDTO pageDTO, Authentication authentication) {
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        pageDTO.setPageId(userDetails.getUsername()); // 세션id가 될 값입니다.
        pageService.updatePage(pageDTO);
        return "redirect:/v1/main";
    }

}
