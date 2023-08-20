package com.spring.holaeat.controller;

import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRepository;
import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.domain.user.UserResponseDto;
import com.spring.holaeat.domain.user_detail.UserDetail;
import com.spring.holaeat.domain.user_detail.UserDetailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

@SessionAttributes({"log","userName"})
@Controller
public class LogController {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final ProfileImgRepository profileImgRepository;

    public LogController(UserRepository userRepository, UserDetailRepository userDetailRepository, ProfileImgRepository profileImgRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
        this.profileImgRepository=profileImgRepository;
    }

    // LoginController.java
    @PostMapping("login")
    public String login(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword, HttpSession session, Model model) {
        User user = userRepository.findByUserId(userId);

        if (user != null) {
            if (user.getUserPassword().equals(userPassword)) {
                UserDetail userDetail = userDetailRepository.findByUserId(userId);
                ProfileImg profileImg = profileImgRepository.findByUserId(userId);

                if (profileImg != null) {
                    session.setAttribute("profileImg", profileImg.getProfileImg());
                    System.out.println(profileImg);
                } else {
                    System.out.println("안되긴 뭐가 안돼용");
                    System.out.println("그럼 돼용?");
                    System.out.println("왜애애옹!");
                    System.out.println("멍멍멍멍!");
                }

                UserResponseDto userResponseDto = new UserResponseDto(user, userDetail);

                model.addAttribute("userResponseDto", userResponseDto);
                session.setAttribute("log", user.getUserId());
                session.setAttribute("userResponseDto", userResponseDto); // 세션에 UserResponseDto 저장

                return "redirect:/";
            } else {
                model.addAttribute("loginFailed", true);
                return "login";
            }
        } else {
            model.addAttribute("loginFailed", true);
            return "login";
        }
    }

    @PostMapping("logout")
    public String logout(HttpSession session, WebRequest request, SessionStatus status) {

        session.invalidate();

        // 우선 호출 후,
        status.setComplete();
        // 세션 속성을 수정
        request.removeAttribute("log", WebRequest.SCOPE_SESSION);
        request.removeAttribute("userName", WebRequest.SCOPE_SESSION);
        return "redirect:/";
    }


}