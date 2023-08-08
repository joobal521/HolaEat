package com.spring.holaeat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String index() {return "index";}

    @GetMapping(value = "join")
    public String join() {return "join";}

    @GetMapping(value = "login")
    public String login() {return "login";}

//    @GetMapping(value = "ingredients")
//    public String ingredients() {return "ingredients";}

    @GetMapping(value = "menu")
    public String menu() {return "menu";}

    @GetMapping(value = "review")
    public String review() {return "review";}


}
