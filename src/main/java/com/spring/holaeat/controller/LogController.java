package com.spring.holaeat.controller;

import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.domain.user_detail.UserDetail;
import com.spring.holaeat.domain.user_detail.UserDetailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@SessionAttributes({"log","userName"})
@Controller
public class LogController {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    public LogController(UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }

    @PostMapping("login")
    public ModelAndView login(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword, HttpSession session, Model model) {
        User user = userRepository.findByUserId(userId); // 사용자 정보를 데이터베이스에서 조회
        UserDetail userDetail = userDetailRepository.findByUserId(userId); // 사용자 상세 정보를 데이터베이스에서 조회

        // 로그인 로직 및 세션 처리
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("log", userId);
        model.addAttribute("userName", user.getUserName());

        session.setAttribute("userAge", user.getUserDetail().getAge());
        session.setAttribute("userHeight", user.getUserDetail().getHeight());
        session.setAttribute("userWeight", user.getUserDetail().getWeight());
        session.setAttribute("userRecCalories", user.getUserDetail().getRecCalories());
        session.setAttribute("userAllergy", user.getUserDetail().getAllergy());
        session.setAttribute("userGender", user.getUserDetail().getGender());

        return modelAndView;
    }

    @PostMapping("logout")
    public String logout(WebRequest request, SessionStatus status) {
        // 우선 호출 후,
        status.setComplete();
        // 세션 속성을 수정
        request.removeAttribute("log", WebRequest.SCOPE_SESSION);
        return "redirect:/";
    }

}