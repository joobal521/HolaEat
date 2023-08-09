package com.spring.holaeat.controller;

import org.springframework.ui.Model;
import com.spring.holaeat.domain.user_detail.UserDetail;
import com.spring.holaeat.domain.user_detail.UserDetailRepository;
import com.spring.holaeat.domain.user_detail.UserDetailRequestDto;
import com.spring.holaeat.service.UserCaloriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Controller
public class UserCaloriesController {
    private final UserDetailRepository userDetailRepository;

    public UserCaloriesController(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }


    @PostMapping(value = "saveCalories")
    public String saveCalories(@ModelAttribute UserDetailRequestDto userDetailDto, HttpSession session, Model model) {
        try {
            String userId = (String) session.getAttribute("log");

            int age = userDetailDto.getAge();
            if (age < 0 || age > 150) {
                return "redirect:/errorPage";
            }

            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(userId);
            userDetail.setGender(userDetailDto.getGender());
            userDetail.setAge(age); // 나이 유효성 검사를 통과한 값으로 설정
            userDetail.setHeight(userDetailDto.getHeight());
            userDetail.setWeight(userDetailDto.getWeight());
            userDetail.setAllergy(userDetailDto.getAllergy());
            userDetail.setRecCalories(userDetailDto.getRecCalories());

            userDetailRepository.save(userDetail);

            model.addAttribute("recSaveResult", "success");
        } catch (Exception e) {
            model.addAttribute("recSaveResult", "error");
        }
        return "redirect:/menu"; // 저장 결과에 따른 알림이 표시될 것
    }

}