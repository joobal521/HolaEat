package com.spring.holaeat.controller;


import com.mysql.cj.jdbc.Blob;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review.ReviewRequestDto;
import com.spring.holaeat.domain.review.ReviewResponseDto;
import com.spring.holaeat.service.ReviewService;
import com.spring.holaeat.util.ImageParsor;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.beans.Transient;
import java.util.*;


@RequiredArgsConstructor
@Controller
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


    // 페이징
@GetMapping("/reviewlist/{pageNumber}")
    public String getBoardAll(@PathVariable int pageNumber,
                                   @RequestParam(required = false) String keyword,
                                   @PageableDefault(size = 10) Pageable pageable,Model model) {

    Map<Long, String> imageMap = new HashMap<>();
    List<Review> list = null;
    if (keyword != null && !keyword.isEmpty()) {
        String pattern = "%" + keyword + "%";
        list = reviewRepository.findAllByTitleLike(pattern, pageable.withPage(pageNumber - 1));
        model.addAttribute("reviewlistPage", list);
    } else {
        list = reviewRepository.findAll(pageable.withPage(pageNumber - 1)).getContent();
        model.addAttribute("reviewlistPage", list);
    }

    for (Review review : list) {
        if (review.getImg() != null) {
            String base64Image = ImageParsor.parseBlobToBase64(review.getImg());
            imageMap.put(review.getReviewNo(), base64Image);
        }
    }

    int totalLength = 0;

    totalLength = reviewRepository.findAll().size();
    if(totalLength>0){
        if(totalLength % 10 !=0){
            pageNumber = totalLength/10 +1;
        }else{
            pageNumber = totalLength/10;
        }
    }

    model.addAttribute("pageNumber",Integer.valueOf(pageNumber));



    model.addAttribute("imageMapPage", imageMap);

    return "reviewlistPage";
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

    //페이징추가

//    @GetMapping("/reviewlist/{pageNumber}")
//    public List<Review> getReviewAll(@PathVariable int pageNumber,
//                                     @RequestParam(required = false) String keyword,
//                                     @PageableDefault(page = 0, size = 10, sort = "reviewNo", direction = Sort.Direction.DESC) Pageable pageable) {
//        if (keyword != null && !keyword.isEmpty()) {
//            String pattern = "%" + keyword + "%";
//             return reviewService.findAllByTitleLikeOrderByReviewNoDesc(pattern, pageable);
//        } else {
//            return reviewService.findAllByOrderByReviewNoDesc();
//        }
//    }



//    @GetMapping("/{pageNumber}")
//    public String paging(@PageableDefault(page=1)Pageable pageable, Model model){
//        Page<ReviewRequestDto> reviewlist = reviewService.paging(pageable);
//
//    }



//// 페이징
//@GetMapping("/reviewlist/{pageNumber}")
//    public List<Review> getBoardAll(@PathVariable int pageNumber,
//                                   @RequestParam(required = false) String keyword,
//                                   @PageableDefault(size = 10) Pageable pageable) {
//
//        if (keyword != null && !keyword.isEmpty()) {
//            String pattern = "%" + keyword + "%";
//            return reviewRepository.findAllByTitleLike(pattern, pageable.withPage(pageNumber - 1));
//        } else {
//            return reviewRepository.findAll(pageable.withPage(pageNumber - 1)).getContent();
//        }
//    }


}
