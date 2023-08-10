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
    public String login(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword, HttpSession session, Model model) {
        User user = userRepository.findByUserId(userId);

        if (user != null) {
            UserDetail userDetail = userDetailRepository.findByUserId(userId);

            session.setAttribute("log", user.getUserId()); // 세션에 로그인된 사용자 이름 저장
            session.setAttribute("userName", user.getUserName()); // 세션에 로그인된 사용자 이름 저장

            if (userDetail != null) {
                session.setAttribute("userAge", userDetail.getAge());
                session.setAttribute("userHeight", userDetail.getHeight());
                session.setAttribute("userWeight", userDetail.getWeight());
                session.setAttribute("userRecCalories", userDetail.getRecCalories());
                session.setAttribute("userAllergy", userDetail.getAllergy());
                session.setAttribute("userGender", userDetail.getGender());
            }

            return "redirect:/"; // 리다이렉트할 때 세션 정보를 유지하고 원하는 도메인으로 이동
        } else {
            // 로그인 실패 처리 로직
            // ...
            return "login"; // 로그인 실패 시 login 페이지로 리다이렉트
        }
    }

    @PostMapping("logout")
    public String logout(WebRequest request, SessionStatus status, HttpSession session) {
        // 우선 호출 후,
        status.setComplete();
        // 세션 속성을 수정
        request.removeAttribute("log", WebRequest.SCOPE_SESSION);
        request.removeAttribute("userName", WebRequest.SCOPE_SESSION);

        UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
        if (userDetail != null) {
            request.removeAttribute("userAge", WebRequest.SCOPE_SESSION);
            request.removeAttribute("userHeight", WebRequest.SCOPE_SESSION);
            request.removeAttribute("userWeight", WebRequest.SCOPE_SESSION);
            request.removeAttribute("userRecCalories", WebRequest.SCOPE_SESSION);
            request.removeAttribute("userAllergy", WebRequest.SCOPE_SESSION);
            request.removeAttribute("userGender", WebRequest.SCOPE_SESSION);
        }

        return "redirect:/";
    }

}