package com.spring.holaeat.controller;

import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import com.spring.holaeat.domain.user_detail.UserDetail;
import com.spring.holaeat.domain.user_detail.UserDetailRepository;
import com.spring.holaeat.domain.user_detail.UserDetailRequestDto;
import com.spring.holaeat.service.UserCaloriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserCaloriesController {
    private final UserDetailRepository userDetailRepository;

    public UserCaloriesController(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }


    @PutMapping(value = "/saveDetails")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> saveDetails(@ModelAttribute UserDetailRequestDto userDetailDto, HttpSession session) {
        try {
            String userId = (String) session.getAttribute("log");

            int age = userDetailDto.getAge();
            if (age < 0 || age > 150) {
                // 나이 유효성 검사 실패 시 에러 응답 반환
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "InvalidAge");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(userId);
            userDetail.setGender(userDetailDto.getGender());
            userDetail.setAge(age); // 나이 유효성 검사를 통과한 값으로 설정
            userDetail.setHeight(userDetailDto.getHeight());
            userDetail.setWeight(userDetailDto.getWeight());
            userDetail.setAllergy(userDetailDto.getAllergy());
            userDetail.setRecCalories(userDetailDto.getRecCalories());
            userDetail.setPrefer(userDetailDto.getPrefer());
            userDetail.setDislike(userDetailDto.getDislike());

            userDetailRepository.save(userDetail);

            // 저장한 정보를 JSON 응답 데이터로 생성
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("age", userDetail.getAge());
            responseData.put("height", userDetail.getHeight());
            responseData.put("weight", userDetail.getWeight());
            responseData.put("allergy", userDetail.getAllergy());
            responseData.put("recCalories", userDetail.getRecCalories());
            responseData.put("Prefer", userDetail.getPrefer());
            responseData.put("Dislike", userDetail.getDislike());

            // 세션 값 변경
            session.setAttribute("userAge", userDetail.getAge());
            session.setAttribute("userHeight", userDetail.getHeight());
            session.setAttribute("userWeight", userDetail.getWeight());
            session.setAttribute("userAllergy", userDetail.getAllergy());
            session.setAttribute("userRecCalories", userDetail.getRecCalories());
            session.setAttribute("userPrefer", userDetail.getPrefer());
            session.setAttribute("userDislike", userDetail.getDislike());

            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            // 에러 처리 로직
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "ServerError");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}