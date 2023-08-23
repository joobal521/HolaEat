package com.spring.holaeat.service;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review_like.ReviewLike;
import com.spring.holaeat.domain.review_like.ReviewLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor
@Service
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;



    public List<ReviewLike> checkReviewLike(String userId, long reviewNo) {
       return reviewLikeRepository.findByUserIdAndReviewNo(userId, reviewNo);
    }

    //추가
    @Autowired
    public ReviewLikeService(ReviewLikeRepository reviewLikeRepository) {
        this.reviewLikeRepository = reviewLikeRepository;
    }

    public void likeReview(String userId, long reviewNo) {
        ReviewLike reviewLike = new ReviewLike();
        reviewLike.setUserId(userId);
        reviewLike.setReviewNo(reviewNo);

        // Save the new like entry using the repository method
        reviewLikeRepository.save(reviewLike);

    }

}
