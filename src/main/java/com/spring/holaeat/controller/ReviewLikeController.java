package com.spring.holaeat.controller;


import com.spring.holaeat.domain.review_like.ReviewLike;
import com.spring.holaeat.domain.review_like.ReviewLikeRepository;
import com.spring.holaeat.service.ReviewLikeService;
import com.spring.holaeat.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
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



//    @Transactional
//    @PutMapping(value = "/reviewlike/{reviewNo}")
//    public void inputReviewLike(@PathVariable long reviewNo,WebRequest request) {
//        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
//
//        System.out.println("컨트롤러 확인 " + reviewNo);
//        System.out.println("컨트롤러 확인 " + log);
//
//        List<ReviewLike> list = reviewLikeService.checkReviewLike(log, reviewNo);
//
//        if(list!=null){
//            reviewService.likeInsert(reviewNo);
//            System.out.println(reviewNo + "- reviewNo(컨트롤러 insert)");
//            //reviewlike 테이블 삭제
//            reviewLikeRepository.deleteByUserIdAndReviewNo(log, reviewNo);
//        }else{
//            reviewService.likeDelete(reviewNo);
//            System.out.println(reviewNo + "- reviewNo(컨트롤러 delete)");
//            //reviewlike 테이블 추가
//
//            ReviewLike newLike = new ReviewLike();
//            newLike.setReviewNo(reviewNo);
//            System.out.println(reviewNo + "- setreviewNo(컨트롤러 delete)");
//            newLike.setUserId(log);
//            System.out.println(log + "- set log(컨트롤러 delete)");
//            reviewLikeRepository.save(newLike);
//            System.out.println(newLike + "- newLike(컨트롤러)");
//
//        }
//
//    }


        @Transactional
        @PostMapping("/reviewlike/{reviewNo}")
        public ResponseEntity<String> likeReview(
                @PathVariable long reviewNo,
                @RequestParam String userId) {

            reviewLikeService.likeReview(userId, reviewNo);

            return ResponseEntity.ok("Review liked successfully!");
        }





        @Transactional
        @PutMapping(value = "/reviewlike/{reviewNo}")
        public void inputReviewLike(@PathVariable long reviewNo,WebRequest request) {
            String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

            System.out.println("컨트롤러 확인 " + reviewNo);
            System.out.println("컨트롤러 확인 " + log);

            List<ReviewLike> list = reviewLikeService.checkReviewLike(log, reviewNo);

            if(list!=null){
                reviewService.likeInsert(reviewNo);
                System.out.println(reviewNo + "- reviewNo(컨트롤러 insert)");
                //reviewlike 테이블 삭제
                reviewLikeRepository.deleteByUserIdAndReviewNo(log, reviewNo);
            }

        }






}




