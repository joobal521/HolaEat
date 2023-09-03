package com.spring.holaeat.controller;


import com.spring.holaeat.domain.review_like.ReviewLike;
import com.spring.holaeat.domain.review_like.ReviewLikeRepository;
import com.spring.holaeat.service.ReviewLikeService;
import com.spring.holaeat.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ReviewLikeController {

    //접근제어자
    private final ReviewService reviewService;
    private final ReviewLikeService reviewLikeService;
    private final ReviewLikeRepository reviewLikeRepository;

    public ReviewLikeController(ReviewService reviewService, ReviewLikeService reviewLikeService, ReviewLikeRepository reviewLikeRepository) {
        this.reviewService = reviewService;
        this.reviewLikeService = reviewLikeService;
        this.reviewLikeRepository = reviewLikeRepository;
    }


        @PostMapping("/reviewlike/{reviewNo}")
        public ResponseEntity<String> likeReview(
                @PathVariable long reviewNo, HttpSession session) {
            String userId = (String) session.getAttribute("log");
            reviewLikeService.likeReview(userId, reviewNo);

            return ResponseEntity.ok("success");
        }


}




