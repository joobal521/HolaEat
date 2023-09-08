package com.spring.holaeat.controller;


import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review_comment.ReviewComment;
import com.spring.holaeat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@SessionAttributes("log")
@Controller
public class MyPageController {
    private final ReviewService reviewService;
    private final ReviewCommentService reviewCommentService;



    @Autowired
    public MyPageController(ReviewService reviewService,ReviewCommentService reviewCommentService) {
        this.reviewService = reviewService;
        this.reviewCommentService=reviewCommentService;

    }


    //내가 쓴 글
    @GetMapping("myReviewPage")
    public String getMyReview(Model model, WebRequest request){
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);


        if (log == null) {
            System.out.println("회원이 아닙니다.");
            // 로그인 페이지로 리다이렉트 또는 오류 처리
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        System.out.println("log는"+log);
        List<Review> reviewList = reviewService.findAllByUserId(log);

        //Pageable adjustedPageable = PageRequest.of(pageNumber - 1, pageable.getPageSize(), pageable.getSort());


//        int totalLength = (int) reviewRepository.count(); // 총 내가 쓴 수 가져오기
//        int totalPages = (int) Math.ceil((double) totalLength / pageable.getPageSize()); // 전체 페이지 수 계산

//        System.out.println("totalLength :" + totalLength );
//        System.out.println("totalPages :" + totalPages );
//        model.addAttribute("pageNumber", pageNumber);
//        model.addAttribute("totalPages", totalPages);
        model.addAttribute("myReviewList", reviewList);


        System.out.println("내가 쓴 글 개수: " + reviewList.size()); // 출력해보기
        return "myReview"; // 내가 작성한 리뷰를 보여주는 페이지의 이름
    }

    //내가 쓴 댓글
    @GetMapping("myCommentPage")
    public String getMyComment(Model model,WebRequest request){
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);


        if (log == null) {
            System.out.println("회원이 아닙니다.");
            // 로그인 페이지로 리다이렉트 또는 오류 처리
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        List<ReviewComment> reviewCommentList= reviewCommentService.findAllByUserId(log);
        model.addAttribute("myCommentList", reviewCommentList);
        System.out.println("내가 쓴 댓글 개수:"+ reviewCommentList.size());
        return "myComment";

    }






}
