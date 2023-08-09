package com.spring.holaeat.controller;

import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
//@Controller
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

//회원가입
@PostMapping(value = "join", consumes = "application/json")
    public  Map join(@RequestBody UserRequestDto userDto){
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

//회원탈퇴
    @DeleteMapping("leave")
    public Map leave(@RequestBody  Map<String, String> requestData){
        String userId = requestData.get("userId");
        JSONObject response =new JSONObject();


        try{
            userService.deleteUserById(userId);
            response.put("result", true);

        }catch (Exception e) {
            e.printStackTrace();
            response.put("result", false);
        }
        return response.toMap();

    }

    //회원정보 수정 PUT
    @PutMapping("user/{username}/update")
    public Map update(@PathVariable String username, @RequestBody UserRequestDto userDto){

        userService.updateUser(username,userDto);
        JSONObject response =new JSONObject();
        response.put("update", "success");


        return response.toMap();
    }




}
