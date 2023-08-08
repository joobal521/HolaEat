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

    // 1. Model 을 활용한 데이터 전달
//    @PostMapping("login")
//    public String login(@RequestBody User user, Model model) {
//        model.addAttribute("log", user.getUsername());
//        return "index";
//    }

    // 2. ModelAndView 를 활용한 데이터 전달
    @SessionScope
    @PostMapping("login")
    public ModelAndView login(@RequestParam("user_id") String userId, @RequestParam("user_password") String userPassword) {
        // Your login logic here
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("log", userId);
        return modelAndView;
    }
}