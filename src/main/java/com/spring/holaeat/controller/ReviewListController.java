package com.spring.holaeat.controller;


import com.mysql.cj.jdbc.Blob;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.service.ReviewService;
import com.spring.holaeat.util.ImageParsor;
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
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.beans.Transient;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ReviewListController {


    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    @GetMapping("/reviewlist")
    public String getReviewAll(Model model) {
        List<Review> list = reviewService.findAllByOrderByReviewNoDesc();
        Map<Long, String> imageMap = new HashMap<>();

        for (Review review : list) {
            if (review.getImg() != null) {
                String base64Image = ImageParsor.parseBlobToBase64(review.getImg());
                imageMap.put(review.getReviewNo(), base64Image);
            }
        }

        model.addAttribute("reviewlist", list);
        model.addAttribute("imageMap", imageMap);
        return "reviewlist";
    }


    //게시글 하나 조회
    @GetMapping("/review/{reviewNo}")
    public String findByReviewNo(Model model, @PathVariable long reviewNo){
        Review review = reviewService.findByReviewNo(reviewNo);
        model.addAttribute("review", review);


        if(review.getImg()==null)
            return "review";

        model.addAttribute("blob", ImageParsor.parseBlobToBase64(review.getImg()));
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
