package com.spring.holaeat.controller;


import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewListController {


    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    @GetMapping("/reviewlist")
    public String getReviewAll(Model model) {
        List<Review> list = reviewService.findAllByOrderByReviewNoDesc();

        model.addAttribute("reviewlist", list);
        return "reviewlist";
    }


    //게시글 하나 조회
    @GetMapping("list/{reviewNo}")
    public List<Review> getBoardByNo(@PathVariable long reviewNo){
        return reviewRepository.findByNo(reviewNo);
    }

    //게시글 목록 조회
    @GetMapping("/list/{pageNumber}")
    public List<Review> getReviewAll(@PathVariable int pageNumber,
                                     @RequestParam(required = false) String keyword,
                                     @PageableDefault(size = 10) Pageable pageable) {

        if (keyword != null && !keyword.isEmpty()) {
            String pattern = "%" + keyword + "%";
            return reviewRepository.findAllByTitleLike(pattern, pageable.withPage(pageNumber - 1));
        } else {
            return reviewRepository.findAll(pageable.withPage(pageNumber - 1)).getContent();
        }
    }

}
