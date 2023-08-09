package com.spring.holaeat.controller;


import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review.ReviewRequestDto;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
//@RequestMapping("/review")
@RestController
//@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    //게시글 작성

//    @PostMapping(value = "/write", consumes ={"multipart/form-data"} )
//    public String write(WebRequest request,@ModelAttribute ReviewRequestDto reviewRequestDto){
//        String log = (String)request.getAttribute("log" , WebRequest.SCOPE_SESSION);
//        System.out.println("log 확인" + log);
//
//        if(log ==null)
//            return "reviewlist";
//
//        reviewRequestDto.setUserId(log);
//        Review review = new Review(reviewRequestDto);
//
//        reviewRepository.save(review);
//        return "reviewlist";
//    }


    @PostMapping(value = "/write", consumes = "multipart/form-data")
    public Map<String,Object> write(WebRequest request, @ModelAttribute ReviewRequestDto reviewRequestDto) {
        JSONObject response = new JSONObject();
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        System.out.println("log 확인" + log);

        try {
            if (log != null) {
                reviewRequestDto.setUserId(log);
                Review review = new Review(reviewRequestDto);

                reviewRepository.save(review);
                response.put("reviewlist", true);

            } else {
                response.put("reviewlist", false);
            }

        } catch (IllegalArgumentException e) {
            response.put("reviewlist", false);

        }

        return response.toMap();

    }


//수정

    @PutMapping(value = "/{reviewNo}/update", consumes = {"multipart/form-data"})
    public Response update(@PathVariable long reviewNo, WebRequest request, @ModelAttribute ReviewRequestDto reviewRequestDto) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        if (log == null) {
            return new Response("update", "로그인 상태에서만 가능합니다.");
        }
        Review review = reviewRepository.findById(reviewNo).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")

        );

        System.out.println(log + " = log 확인용");

        if (!review.getUserId().equals(log)) {
            return new Response("update", "작성자만 수정할 수 있습니다.");
        }

        reviewService.update(review, reviewRequestDto);

        System.out.println("수정 성공" + " log : " + log);
        return new Response("update", "success");
    }

//삭제

    @DeleteMapping("/{reviewNo}/delete")
    public Response delete(@PathVariable long reviewNo, WebRequest request, @ModelAttribute ReviewRequestDto reviewRequestDto) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        if (log == null) {
            return new Response("delete", "로그인 상태에서만 가능합니다.");
        }

        Review review = reviewRepository.findById(reviewNo).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );


        if (!review.getUserId().equals(log)) {
            return new Response("delete", "작성자만 삭제할 수 있습니다.");
        }

        reviewService.delete(reviewNo);
        System.out.println("게시글 삭제");

        return new Response("delete", "success");

    }


}


