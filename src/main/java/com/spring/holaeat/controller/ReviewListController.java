package com.spring.holaeat.controller;


import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.service.ReviewService;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @GetMapping("/review/{reviewNo}")
    public String findByReviewNo(Model model, @PathVariable long reviewNo){
        List<Review> list = reviewService.findByReviewNo(reviewNo);
//        System.out.println("list1" + list.get(0).getUserId());
        model.addAttribute("review",list);

        return "review";
    }

    //게시글 목록 조회
    @GetMapping("/reviewlist/{pageNumber}")
    public List<Review> getReviewAll(@PathVariable int pageNumber,
                                     @RequestParam(required = false) String keyword,
                                     @PageableDefault(page=0,size = 10,sort = "reviewNo", direction = Sort.Direction.DESC) Pageable pageable) {

        if (keyword != null && !keyword.isEmpty()) {
            String pattern = "%" + keyword + "%";
            return reviewRepository.findAllByTitleLike(pattern, pageable.withPage(pageNumber - 1));
        } else {
            return reviewRepository.findAll(pageable.withPage(pageNumber - 1)).getContent();
        }
    }


}
