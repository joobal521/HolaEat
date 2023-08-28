package com.spring.holaeat.controller;


import com.mysql.cj.jdbc.Blob;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review.ReviewRequestDto;
import com.spring.holaeat.domain.review.ReviewResponseDto;
import com.spring.holaeat.domain.review_comment.ReviewComment;
import com.spring.holaeat.domain.review_comment.ReviewCommentRepository;
import com.spring.holaeat.domain.review_like.ReviewLike;
import com.spring.holaeat.domain.review_like.ReviewLikeRepository;
import com.spring.holaeat.service.ReviewLikeService;
import com.spring.holaeat.service.ReviewService;
import com.spring.holaeat.util.ImageParsor;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.beans.Transient;
import java.util.*;


@RequiredArgsConstructor
@Controller
public class ReviewListController {


    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewLikeService reviewLikeService;


    // 페이징,검색
    @GetMapping("/reviewlist/{pageNumber}")
    public String getBoardAll(@PathVariable int pageNumber,
                              @RequestParam(required = false, value = "keyword") String keyword,
                              @RequestParam(required = false, value = "searchType") String searchType,
                              @PageableDefault(size = 8) Pageable pageable, Model model, HttpSession session) {

        Map<Long, String> imageMap = new HashMap<>();
        List<Review> reviewPage = null;

        Pageable adjustedPageable = PageRequest.of(pageNumber - 1, pageable.getPageSize(), pageable.getSort());

        //검색
        if (keyword != null && !keyword.isEmpty()) {
            String pattern = "%" + keyword + "%";
            if ("title".equals(searchType)) {
                reviewPage = reviewRepository.findAllByTitleLike(pattern, adjustedPageable);
            }
            if ("author".equals(searchType)) {
                reviewPage = reviewRepository.findAllByUserIdLike(pattern, adjustedPageable);
            }
            if("all".equals(searchType)){
                reviewPage = reviewRepository.findAllByTitleLikeOrUserIdLike(pattern, adjustedPageable);
            }
        } else {
            reviewPage = reviewRepository.findAllByOrderByReviewNoDesc(adjustedPageable);
        }


        String userId = (String) session.getAttribute("log");


        model.addAttribute("reviewlistPage", reviewPage); // reviewPage를 모델에 추가



        // 가져온 리뷰 목록에 대해 각 리뷰에 대한 좋아요 여부를 확인하고 모델에 추가
        List<ReviewLike> likedList = new ArrayList<>();
        // 하트 상태를 heartMapPage에 매핑
        Map<Long,Integer> heartMapPage = new HashMap<>();

        for (Review review : reviewPage) {

            ReviewLike isLiked =  reviewLikeService.checkReviewLike(userId, review.getReviewNo());
            likedList.add(isLiked);

            int heartStatus = isLiked != null ? 1 : 0; // 좋아요 여부에 따라 하트 상태 결정
            heartMapPage.put(review.getReviewNo(), heartStatus);

            //이미지 출력
            if (review.getImg() != null) {
                String base64Image = ImageParsor.parseBlobToBase64(review.getImg());
                imageMap.put(review.getReviewNo(), base64Image);
            }
        }

        int totalLength = (int) reviewRepository.count(); // 총 리뷰 수 가져오기
        int totalPages = (int) Math.ceil((double) totalLength / pageable.getPageSize()); // 전체 페이지 수 계산

        System.out.println("totalLength :" + totalLength );
        System.out.println("totalPages :" + totalPages );
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("reviewlistPage", reviewPage);
        model.addAttribute("imageMapPage", imageMap);
        model.addAttribute("heartMapPage",heartMapPage);

        model.addAttribute("likedList", likedList); // 리뷰에 대한 좋아요 여부 목록 추가
        System.out.println(likedList+"likedList확인");
        return "reviewlistPage";
    }


    //게시글 하나 조회
    @GetMapping("/review/{reviewNo}")
    public String findByReviewNo(Model model, @PathVariable long reviewNo){
        Review review = reviewService.findByReviewNo(reviewNo);
        model.addAttribute("review", review);

        List<ReviewComment>  reviewComment = reviewCommentRepository.findAllByReviewNo(reviewNo);
        model.addAttribute("reviewComment", reviewComment);


        if(review.getImg()==null)
            return "review";

        model.addAttribute("blob", ImageParsor.parseBlobToBase64(review.getImg()));
        return "review";

    }


}