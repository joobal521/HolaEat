package com.spring.holaeat.controller;


import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
public class ReviewLikeController {

    //접근제어자
    private final ReviewService reviewService;

    public ReviewLikeController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Transactional
    @PutMapping(value = "/reviewlike/{reviewNo}")
    public void insert(@PathVariable long reviewNo) {
        System.out.println("컨트롤러 확인 " + reviewNo);
        reviewService.insert(reviewNo);
    }

}
