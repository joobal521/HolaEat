package com.spring.holaeat.controller;


import com.spring.holaeat.domain.review_like.ReviewLike;
import com.spring.holaeat.domain.review_like.ReviewLikeRepository;
import com.spring.holaeat.service.ReviewLikeService;
import com.spring.holaeat.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;


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

    @Transactional
    @PutMapping(value = "/reviewlike/{reviewNo}")
    public void putReviewLike(@PathVariable long reviewNo,WebRequest request) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        System.out.println("컨트롤러 확인 " + reviewNo);
        System.out.println("컨트롤러 확인 " + log);



        List<ReviewLike> list = reviewLikeService.checkReviewLike(log, reviewNo);

        if(list!=null){
            reviewService.likeInsert(reviewNo);
            //reviewlike 테이블 삭제
            reviewLikeRepository.deleteByUserIdAndReviewNo(log, reviewNo);
        }else{
            reviewService.likeDelete(reviewNo);
            //reviewlike 테이블 추가

            ReviewLike newLike = new ReviewLike();
            newLike.setReviewNo(reviewNo);
            newLike.setUserId(log);
            reviewLikeRepository.save(newLike);
        }

    }

}
