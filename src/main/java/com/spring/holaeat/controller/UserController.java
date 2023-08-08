package com.spring.holaeat.controller;

import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RequiredArgsConstructor
@RestController

@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

//회원가입
@PostMapping("join")
    public String join(@ModelAttribute UserRequestDto userDto){

    try{
        userService.getUserById(userDto.getUserId());
        System.out.println("join fail");

    }catch (IllegalArgumentException e){
        userService.createUser(userDto);
        System.out.println("join success");

    }
    return "login";

}




}
