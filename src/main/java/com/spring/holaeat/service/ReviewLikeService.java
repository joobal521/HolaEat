package com.spring.holaeat.service;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review_like.ReviewLike;
import com.spring.holaeat.domain.review_like.ReviewLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void likeReview(String userId, long reviewNo) {
        List<ReviewLike> list = reviewLikeRepository.findByUserIdAndReviewNo(userId, reviewNo);

        if(list.size() == 0) {
            ReviewLike reviewLike = new ReviewLike(reviewNo, userId);
            reviewLikeRepository.save(reviewLike);
            return;
        }

        reviewLikeRepository.delete(list.get(0));
    }

}
