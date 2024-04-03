package com.example.askproject.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

import com.example.askproject.Exception.PageException;
import com.example.askproject.Exception.UserException;
import com.example.askproject.Model.DTO.AnswerDTO;
import com.example.askproject.Model.DTO.MessageDTO;
import com.example.askproject.Model.DTO.PageDTO;
import com.example.askproject.Model.DTO.QuestionDTO;
import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Model.DTO.joindQnaDTO;
import com.example.askproject.Service.AnswerService;
import com.example.askproject.Service.PageService;
import com.example.askproject.Service.QuestionService;
import com.example.askproject.Service.UserService;
import com.example.askproject.Service.UserServiceSecurity;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private PageService pageService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserServiceSecurity userServiceSecurity;

    @GetMapping("/main")
    public String getMainPage(Model model, Authentication authentication) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("myId", userDetails.getUsername());
        model.addAttribute("Nickname", userService.findNicknameByUserId(userDetails.getUsername()));
        List<Map<String, Object>> countQuestions = questionService.countQuestionByUserId();
        List<Map<String, Object>> countAnswers = answerService.countAnswerByUserId();
        List<PageDTO> pageDTOs = pageService.findAllPage();
        List<PageDTO> selectedPages = pageDTOs.size() <= 5
                ? pageDTOs
                : getRandomPages(pageDTOs, 5);
        model.addAttribute("countQuestion", countQuestions);
        model.addAttribute("countAnswer", countAnswers);
        model.addAttribute("pages", selectedPages);
        return "user/main";
    }

    private List<PageDTO> getRandomPages(List<PageDTO> allPages, int count) throws Exception{
        List<PageDTO> shuffledPages = new ArrayList<>(allPages);
        Collections.shuffle(shuffledPages);

        // count만큼 선택
        return shuffledPages.subList(0, count);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<String> searchUsers(@RequestParam("query") String query) throws Exception{
        return pageService.findByPageIdContaining(query);
    }

    @GetMapping("/page")
    public String getIndividualPage(@RequestParam String id, Model questionModel, Authentication authentication) throws Exception{
        if (!userService.findAllUserId().contains(id)) {
            throw new UserException("계정을 찾을 수 없음");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        questionModel.addAttribute("Nickname", userService.findNicknameByUserId(userDetails.getUsername()));
        questionModel.addAttribute("myId", userDetails.getUsername());
        pageService.increasePageCount(id);

        List<joindQnaDTO> qdtos = questionService.joinQuestionAnswerByQuestionTo(id, userDetails.getUsername());
        PageDTO pageDTO = pageService.findByPageId(id);
        questionModel.addAttribute("pageTitle", pageDTO.getPageTitle());
        questionModel.addAttribute("pageComment", pageDTO.getPageComment());
        questionModel.addAttribute("pageTodayCount", pageDTO.getPageTodayCount());
        int questionCount = qdtos.size();
        int answerCount = answerService.findAllByAnswerFrom(id).size();
        boolean isEquals = id.equals(userDetails.getUsername()); // to1을 세션 아이디로 바꿔야댐
        questionModel.addAttribute("questionCount", questionCount);
        questionModel.addAttribute("answerCount", answerCount);
        questionModel.addAttribute("noAnswerCount", questionCount - answerCount);
        questionModel.addAttribute("idCheck", isEquals);
        questionModel.addAttribute("pageId", id);
        questionModel.addAttribute("questions", qdtos);
        return "user/mypage";
    }

    @PostMapping("/sendq")
    public String postQuestion(@ModelAttribute QuestionDTO questionDTO, @RequestParam String id,
            Authentication authentication) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        questionDTO.setQuestionTo(id);
        questionDTO.setQuestionFrom(userDetails.getUsername());
        questionService.insertQuestion(questionDTO);
        return "redirect:/user/page?id=" + id;
    }

    @PostMapping("/senda")
    public String postAnswer(@ModelAttribute AnswerDTO answerDTO, @RequestParam String pageid,
            @RequestParam("questionid") Long questionid, @RequestParam("from") String from,
            Authentication authentication) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        answerDTO.setAnswerTo(from);
        answerDTO.setAnswerQuestionId(questionid);
        answerDTO.setAnswerFrom(userDetails.getUsername());// 세션아이디가 될 예정
        answerService.insertAnswer(answerDTO);
        questionService.changeAnswered(questionid);
        return "redirect:/user/page?id=" + pageid;
    }

    @PostMapping("/deletea")
    public void deleteAnswer(@RequestParam Long answerId, @RequestParam Long questionId,
            Authentication authentication) throws Exception{
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (answerService.checkMyAnswer(userDetails.getUsername(), answerId)) {
            answerService.deleteByAnswerId(answerId);
            questionService.changeAnswered(questionId);
        } else {
            throw new UserException("비정상적인 답변 삭제 시도");
        }
    }

    @PostMapping("/deleteq")
    public void deleteQuestion(@RequestParam Long questionId, Authentication authentication) throws Exception{
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (questionService.checkMyQuestion(userDetails.getUsername(), questionId)
                || questionService.checkMyQuestionTo(userDetails.getUsername(), questionId)) {
            questionService.deleteByQuestionId(questionId);
            answerService.deleteAnswerCascade(questionId);
        } else {
            throw new UserException("비정상적인 질문 삭제 시도");
        }
    }

    @PostMapping("/updateq")
    public void updateQuestion(@RequestParam Long questionId, @RequestParam String updatedQuestion,
            Authentication authentication) throws Exception{
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (questionService.checkMyQuestion(userDetails.getUsername(), questionId)) {
            questionService.updateQuestionContent(questionId, updatedQuestion);
        } else {
            throw new UserException("비정상적인 질문 수정 시도");
        }
    }

    @PostMapping("/updatea")
    public void updateAnswer(@RequestParam Long answerId, @RequestParam String updatedAnswer,
            Authentication authentication) throws Exception{
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (answerService.checkMyAnswer(userDetails.getUsername(), answerId)) {
            answerService.updateAnswerContent(answerId, updatedAnswer);
        } else {
            throw new UserException("비정상적인 답변 수정 시도");
        }
    }

    @GetMapping("/pagesetting")
    public String getPageSetting(Model settingModel, Authentication authentication) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        settingModel.addAttribute("Nickname", userService.findNicknameByUserId(userDetails.getUsername()));
        settingModel.addAttribute("myId", userDetails.getUsername());
        PageDTO pagedto = pageService.findByPageId(userDetails.getUsername());
        settingModel.addAttribute("pageDTO", pagedto);
        UserDTO userdto = userService.findByUserId(userDetails.getUsername());
        settingModel.addAttribute("userDTO", userdto);
        return "user/pagesetting";
    }

    @PostMapping("/pageupdate")
    public String updatePageSetting(@ModelAttribute PageDTO pageDTO, Authentication authentication, Model model) throws Exception{
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        pageDTO.setPageId(userDetails.getUsername()); // 세션id가 될 값입니다.
        pageService.updatePage(pageDTO);
        MessageDTO message = new MessageDTO("페이지 정보가 업데이트 되었습니다.", "/user/main", RequestMethod.GET);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/updatepassword")
    public String updatePassword(@ModelAttribute UserDTO userDTO, Authentication authentication, Model model) throws Exception{
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDTO.setUserId(userDetails.getUsername()); // 세션id가 될 값입니다.
        userServiceSecurity.changePassword(userDTO);
        MessageDTO message = new MessageDTO("비밀번호 수정이 완료되었습니다.", "/user/main", RequestMethod.GET);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/updatenickname")
    public String updateNickname(@ModelAttribute UserDTO userDTO, Authentication authentication, Model model) throws Exception{
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDTO.setUserId(userDetails.getUsername()); // 세션id가 될 값입니다.
        userService.updateUser(userDTO);
        MessageDTO message = new MessageDTO("닉네임이 수정이 완료되었습니다.", "/user/main", RequestMethod.GET);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/deleteuser")
    public String deleteUser(Authentication authentication) throws Exception{
        // TODO: process POST request
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userService.deleteUser(userDetails.getUsername());
        pageService.deletePage(userDetails.getUsername());
        return "redirect:/logout";
    }

    private String showMessageAndRedirect(final MessageDTO params, Model model)throws Exception{
        model.addAttribute("params", params);
        return "etc/messageRedirect";
    }

    @PostMapping("/verify-password")
    @ResponseBody
    public boolean verifyPassword(@RequestParam String currentPassword, Authentication authentication) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userServiceSecurity.verifyPassword(userDetails.getUsername(), currentPassword);
    }
}
