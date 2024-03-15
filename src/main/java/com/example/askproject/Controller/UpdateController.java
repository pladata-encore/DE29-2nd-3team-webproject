package com.example.askproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.askproject.Model.DTO.PageDTO;
import com.example.askproject.Service.PageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/v1/user")
@Controller
public class UpdateController {
    @Autowired
    private PageService pageService;

    @GetMapping("/modifypage")
    public String updatePage(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession();
    if (session.getAttribute("pageId") == null) {
        return "redirect:/v1/user/loginPage";
    }
    Long pageId = (Long) session.getAttribute("pageId");
    PageDTO pageDTO = pageService.getPageById(pageId);
    model.addAttribute("currentPage", pageDTO);
    return "pageupdate";
}
}

    @PostMapping("/modifypage/{userid}")
    public String updatePage(@Valid @ModelAttribute("pageDTO") PageDTO dto, BindingResult result, @PathVariable String userid) {
        if (result.hasErrors()) {
            return "pageupdate";
        }
        dto.setPageId(userid);
        pageService.updatePage(dto);
        return "redirect:/v1/user/page";
    }
}

