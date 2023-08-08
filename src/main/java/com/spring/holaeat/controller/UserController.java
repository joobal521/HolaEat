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
//@Controller
@RestController
//@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

//회원가입
@PostMapping(value = "/join", consumes = "application/json")
    public  Map<String, Object> join(@RequestBody UserRequestDto userDto){
    JSONObject response =new JSONObject();


    try{
        userService.getUserById(userDto.getUserId());
        System.out.println("join fail");
        response.put("result", false);


    }catch (IllegalArgumentException e){
        userService.createUser(userDto);
        System.out.println("join success");
        response.put("result",true);

    }
    return response.toMap();


}




}
