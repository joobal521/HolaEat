package com.spring.holaeat.controller;

import com.spring.holaeat.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class EmailController {
    private final EmailSendService emailSendService;



    //이메일 인증
   @PostMapping("emailCheck")
    public Map emailCheck(@RequestBody  Map<String, String> requestData, HttpServletRequest request) {
        String userEmail=requestData.get("userEmail"); //이메일


       JSONObject response =new JSONObject();

       try {
           String verificationCode = emailSendService.joinEmail(userEmail);
           HttpSession session = request.getSession();
           session.setAttribute("VERIFICATION_CODE", verificationCode);

           response.put("result", true);


       } catch (Exception e) {
           response.put("result", false);

           e.printStackTrace();
       }

       System.out.println("이메일 인증 요청이 들어옴!");
       System.out.println("이메일 인증 이메일 : " + userEmail);


       return response.toMap();
    }












}
