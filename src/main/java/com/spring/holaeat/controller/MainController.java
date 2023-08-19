package com.spring.holaeat.controller;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final ReviewRepository reviewRepository;

    @GetMapping(value = "/")
    public String index() {return "index";}

    @GetMapping(value = "join")
    public String join() {return "join";}

    @GetMapping(value = "login")
    public String login() {return "login";}


    //아이디 찾기
    @GetMapping(value = "find")
    public String findId(){return "findUser";}

    //비빌번호 찾기


    @GetMapping(value ="leave")
    public String leave(){return "leaveForm";}

    @GetMapping("update")
    public String update(){return "updateForm";}

//    @GetMapping(value = "ingredients")
//    public String ingredients() {return "ingredients";}

//    @GetMapping(value = "menu")
//    public String menu() {return "menu";}

//    @GetMapping(value = "review")
//    public String review() {return "review";}

    @GetMapping(value = "reviewform")
    public String reviewForm() {return "reviewform";}

    @GetMapping(value = "mypage")
    public String myPage() {return "myPage";}

    @GetMapping(value = "myinfo")
    public String myInfo(){return "myInfo";}

    @GetMapping(value = "gainpower")
    public String admin_login() {return "gainpower";}

    @GetMapping(value = "admin")
    public String admin() {return "admin";}

    //건강 정보
    @GetMapping(value = "healthForm")
    public String adminHealthFrom(){return "adminHealthForm";}


    @GetMapping(value = "healthUpdate")
    public String healthUpdate(){
        return "adminHealthUpdate";}

    @GetMapping(value = "adminUser")
    public String adminUser() {return "adminUser";}

    @GetMapping(value = "/reviewUpdate")
    public String reviewUpdate(@RequestParam("reviewNo") long reviewNo, Model model) {
        Optional<Review> review = reviewRepository.findById(reviewNo);

        if(review == null)
            return "reviewlistPage";

        model.addAttribute("review", review.get());
        return "reviewUpdate";
    }




    @GetMapping("term")
    public String term(){return"term";}

    @GetMapping("term2")
    public String term2(){return "term2";}


}
