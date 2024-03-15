package com.example.askproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getUserSetting(@RequestParam String id, Model settingModel) {
        UserDTO userdto = userService.findByUserId(id);
        settingModel.addAttribute("userdto", userdto);
        return "myaccount";
    }

    @GetMapping("/page")
    public String getPageSetting(@RequestParam String id, Model settingModel) {
        PageDTO pagedto = pageService.findByPageId(id);
        settingModel.addAttribute("pageDTO", pagedto);
        return "pagesetting";
    }

    @PostMapping("/pageupdate")
    public String updatePageSetting(@ModelAttribute PageDTO pageDTO) {
        //TODO: process POST request
        pageDTO.setPageId("to1"); //세션id가 될 값입니다.
        pageService.updatePage(pageDTO);
        return "redirect:/v1/main";
    }
    
    
    
}
