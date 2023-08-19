package com.spring.holaeat.controller;


import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.ProfileImgService;
import com.spring.holaeat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
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

//        ProfileImgRequestDto profileImgDto = new ProfileImgRequestDto();
//        profileImgService.createProfile(profileImgDto);

        System.out.println("join success");
        response.put("result",true);

    }
    return response.toMap();

}

//아이디 찾기
    @PostMapping(value = "findId")
    public ResponseEntity<String> findIdByEmailAndName(@RequestParam String userEmail, @RequestParam String userName) {
        User user = userService.findUserByEmailAndName(userEmail, userName);
        if (user != null) {
            return ResponseEntity.ok(user.getUserId());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디를 찾을 수 없습니다.");
        }
    }

//이미지 파일 변환

//    private byte[] getDefaultImageBytes() throws IOException {
//        try (InputStream is = getClass().getClassLoader().getResourceAsStream("static/img/belle.jpg")) {
//            if (is != null) {
//                return is.readAllBytes();
//            }
//            throw new IOException("기본 이미지 파일을 읽을 수 없습니다.");
//        }
//    }

//아이디 중복체크
    @PostMapping("userId-check")
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

    //이메일 중복체크
    @PostMapping("userEmail-check")
    public Map checkUserEmail(@RequestBody  Map<String, String> requestData) {
        String userEmail=requestData.get("userEmail");
        boolean dupl=userService.duplCheckUserEmail(userEmail);
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
                //profileImgService.deleteProfile(profileImg);
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

    //회원정보 수정
    @PutMapping("update")
    public Map update(@RequestBody Map<String, String> requestData , WebRequest request){
    JSONObject response = new JSONObject();
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        if (log == null) {
            response.put("result", false);
        }
        System.out.println("로그인:"+log);

        String userId = requestData.get("userId");
        String userPassword = requestData.get("userPassword");
        String newPassword = requestData.get("newPassword");
        String newPasswordCh = requestData.get("newPasswordCh");
        String userEmail = requestData.get("userEmail");
        String userName = requestData.get("userName");



        try {
            User user = userService.getUserById(userId);
            if(user!=null && user.getUserId().equals(userId)) {
                if (user.getUserPassword().equals(userPassword)) {
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
            }else{
                response.put("result",false);
                System.out.println("회원이 아닌뎅?");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("result", false);
            response.put("message", "회원 정보 수정 실패");
        }

        response.put("update", "success");

        return response.toMap();
    }

    //비밀번호 찾기


//    @PostMapping("verification-requests")
//    public ResponseEntity sendMessage(@RequestParam("email") String email) {
//        userService.sendCodeToEmail(email);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//이메일 인증
    @PostMapping("verification-email")
    public String sendMessage(@RequestBody  Map<String, String> requestData) {
        String email = requestData.get("userEmail");
        System.out.println("이메일"+email);

        try {
            return userService.sendCodeToEmail(email);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("인증번호 전송 실패");

        }

        return userService.sendCodeToEmail(email);

    }



    //비밀번호 보내기
//    @PostMapping("fine-password")
//    public String sendPassword(@RequestBody Map<String, String> requestData){
//
//    }


    //이메일 인증 확인
//    @GetMapping("verification")
//    public Map verificationEmail(@RequestParam("email") String email,
//                                            @RequestParam("code") String authCode) {
//        JSONObject response = new JSONObject();
//        try {
//            userService.verifiedCode(email, authCode);
//            response.put("result", true);
//
//        }catch (Exception e){
//            response.put("result", false);
//            response.put("message", "인증코드 확인 실패");
//        }
//
//        return response.toMap();
//    }





}