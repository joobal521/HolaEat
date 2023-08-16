package com.spring.holaeat.controller;

import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.service.ProfileImgService;
import com.spring.holaeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ProfileImgService profileImgService;

//회원가입
@PostMapping(value = "join", consumes = "application/json")
    public  Map join(@RequestBody UserRequestDto userDto){
    JSONObject response =new JSONObject();

    try{
        userService.getUserById(userDto.getUserId());
        System.out.println("join fail");
        response.put("result", false);


    }catch (IllegalArgumentException e){

        //회원가입
        userService.createUser(userDto);

        // 프로필 이미지 생성
        ProfileImgRequestDto profileImgDto = new ProfileImgRequestDto();
        profileImgDto.setUserId(userDto.getUserId());
        profileImgService.createProfile(profileImgDto);

        System.out.println("join success");
        response.put("result",true);

    }
    return response.toMap();

}

//이미지 파일 변환

    private byte[] getDefaultImageBytes() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("static/img/belle.jpg")) {
            if (is != null) {
                return is.readAllBytes();
            }
            throw new IOException("기본 이미지 파일을 읽을 수 없습니다.");
        }
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