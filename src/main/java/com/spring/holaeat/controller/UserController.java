package com.spring.holaeat.controller;


import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import com.spring.holaeat.domain.review_like.ReviewLikeRepository;
import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final ReviewCommentService reviewCommentService;
    private final ProfileImgService profileImgService;
    private final ReviewLikeService reviewLikeService;


//회원가입
@PostMapping(value = "join", consumes = "application/json")
    public  Map join(@RequestBody UserRequestDto userDto){
    JSONObject response =new JSONObject();

    try{
        userService.getUserByUserId(userDto.getUserId());
        System.out.println("join fail");
        response.put("result", false);


    }catch (IllegalArgumentException e){


        //회원가입
        userService.createUser(userDto);

        // 프로필 이미지 생성
        ProfileImgRequestDto profileImgDto = new ProfileImgRequestDto();
        profileImgService.createProfile(profileImgDto, userDto.getUserId()); // userId 전달


        System.out.println("join success");
        response.put("result",true);

    }
    return response.toMap();

}

//아이디 찾기
    @PostMapping(value = "find-userId")
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
    @PostMapping("check-userId")
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
    @PostMapping("check-userEmail")
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
    @Transactional
    @DeleteMapping("leave")
    public Map leave(@RequestBody  Map<String, String> requestData, HttpSession session){
        String userId = requestData.get("userId");
        String userPassword = requestData.get("userPassword"); // 입력한 비밀번호


        JSONObject response =new JSONObject();

        try{

            User user = userService.getUserByUserId(userId);

            if (user != null && user.getUserPassword().equals(userPassword)) {

                // 먼저 외래 키로 연결된 자식 레코드를 삭제합니다.
                // 예를 들어, 'Review' 테이블이 'user_id'라는 외래 키를 가지고 있다고 가정하겠습니다.
                // 이 경우에는 해당 유저와 관련된 리뷰 레코드를 모두 삭제해야 합니다.
                reviewLikeService.deleteLikeByUserId(userId);
                reviewCommentService.deleteReviewCommentByUserId(userId);
                reviewService.deleteReviewsByUserId(userId);
                profileImgService.deleteProfile(userId);

                //부모 테이블 삭제
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
            User user = userService.getUserByUserId(userId);
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
@PutMapping( "find-pwd")
public Map changePwd (@RequestBody Map<String, String> requestData,WebRequest request){
    JSONObject response = new JSONObject();
    String userEmail = (String)request.getAttribute("userEmail", WebRequest.SCOPE_SESSION);
    String newPassword = requestData.get("newPassword");
    String newPasswordCh = requestData.get("newPasswordCh");
    System.out.println(userEmail+"님의 비밀번호를 바꿀~");

    try{
        UserRequestDto updatedUserDto = new UserRequestDto();
        updatedUserDto.setUserPassword(newPassword);
        userService.updateNewPwd(userEmail,updatedUserDto);
        response.put("result", true);
        response.put("message", "비밀번호가 성공적으로 변경되었습니다.");

    }catch (Exception e){
        e.printStackTrace();
        response.put("result", false);
        response.put("message", "비밀번호가 성공적으로 변경되었습니다.");
        System.out.println("비밀번호 바꾸기 실패");

    }
    return response.toMap();

}

    //비밀번호 찾기-이메일로 보내서
//    @PostMapping("verification-requests")
//    public ResponseEntity sendMessage(@RequestParam("email") String email) {
//        userService.sendCodeToEmail(email);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//이메일 인증
    @PostMapping("verification-email")
    public String sendMessage(@RequestBody  Map<String, String> requestData,HttpSession session) {
        String email = requestData.get("userEmail");
        System.out.println("이메일"+email);

        try {
            session.setAttribute("userEmail", email);
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