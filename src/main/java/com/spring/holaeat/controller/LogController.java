package com.spring.holaeat.controller;

import com.spring.holaeat.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes({"log"})
@Controller
public class LogController {

    @SessionScope
    @PostMapping("login")
    public ModelAndView login(@RequestParam("user_id") String userId, @RequestParam("user_password") String userPassword) {
        // Your login logic here
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("log", userId);
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