package com.spring.holaeat.controller;

import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller

//@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

//회원가입
@PostMapping("/join")
    public String join(@ModelAttribute UserRequestDto userDto){


    try{
        userService.getUserById(userDto.getUserId());
        System.out.println("join fail");
        //response.put("leave", "fail");


    }catch (IllegalArgumentException e){
        userService.createUser(userDto);
        System.out.println("join success");
        //response.put("join","success");

    }
    return "login";


}








}
