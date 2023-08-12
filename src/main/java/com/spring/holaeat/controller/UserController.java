package com.spring.holaeat.controller;

import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
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

//아이디 중복체크
    @PostMapping("userIdDupl")
    public Map checkUserId(@RequestBody  Map<String, String> requestData) {
        String userId=requestData.get("userId");
        boolean dupl = userService.duplCheckUserId(userId);
        JSONObject response =new JSONObject();


        if(!dupl){
            response.put("result", true);

        }else {
            response.put("result", false);
        }
        return response.toMap();
    }

//회원탈퇴
    @DeleteMapping("leave")
    public Map leave(@RequestBody  Map<String, String> requestData, HttpSession session){
        String userId = requestData.get("userId");
        String userPassword = requestData.get("userPassword"); // 입력한 비밀번호

        JSONObject response =new JSONObject();

        try{

            User user = userService.getUserById(userId);

            if (user != null && user.getUserPassword().equals(userPassword)) {

                userService.deleteUserById(userId);
                session.removeAttribute("log"); // 세션에서 log 속성 제거
                response.put("result", true);
            }else{
                response.put("result", false);
            }
        }catch (Exception e) {
            e.printStackTrace();
            response.put("result", false);
        }
        return response.toMap();

    }

    //회원정보 수정 PUT
    @PutMapping("update")
    public Map update(@RequestBody  Map<String, String> requestData){
        String userId = requestData.get("userId");
        String userPassword = requestData.get("userPassword");
        String newPassword = requestData.get("newPassword");
        String newPasswordCh = requestData.get("newPasswordCh");
        String userEmail = requestData.get("userEmail");
        String userName = requestData.get("userName");



        JSONObject response = new JSONObject();

        try {
            User user = userService.getUserById(userId);

            if (user != null && user.getUserPassword().equals(userPassword)) {
                if (newPassword.equals(newPasswordCh)) {
                    UserRequestDto updatedUserDto = new UserRequestDto();
                    updatedUserDto.setUserId(userId);
                    updatedUserDto.setUserPassword(newPassword);
                    updatedUserDto.setUserEmail(userEmail);
                    updatedUserDto.setUserName(userName);

                    userService.updateUser(userId, updatedUserDto);

                    response.put("result", true);
                } else {
                    response.put("result", false);
                    response.put("message", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
                }
            } else {
                response.put("result", false);
                response.put("message", "기존 비밀번호가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("result", false);
            response.put("message", "회원 정보 수정 실패");
        }

        response.put("update", "success");

        return response.toMap();
    }





}