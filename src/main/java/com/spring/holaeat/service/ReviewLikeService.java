package com.spring.holaeat.service;

import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review_like.ReviewLike;
import com.spring.holaeat.domain.review_like.ReviewLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@RequiredArgsConstructor
@Service
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewRepository reviewRepository;

//    public List<ReviewLike> checkReviewLike(String userId, long reviewNo) {
//        return reviewLikeRepository.findByUserIdAndReviewNo(userId, reviewNo);
//    }
    public ReviewLike checkReviewLike(String userId, long reviewNo) {
        return reviewLikeRepository.findByUserIdAndReviewNo(userId, reviewNo);
    }


    //추가
    @Autowired
    public ReviewLikeService(ReviewLikeRepository reviewLikeRepository, ReviewRepository reviewRepository) {
        this.reviewLikeRepository = reviewLikeRepository;
        this.reviewRepository = reviewRepository;
    }



    @Transactional
    public void likeReview(String userId, long reviewNo) {
        ReviewLike list = reviewLikeRepository.findByUserIdAndReviewNo(userId, reviewNo);

        if (list==null) {
            // 좋아요가 없는 경우 좋아요 생성
            ReviewLike reviewLike = new ReviewLike(reviewNo, userId);
            reviewLikeRepository.save(reviewLike);
            reviewRepository.likeCountByReviewNo(reviewNo);
        } else {
            // 이미 좋아요가 있는 경우 좋아요 삭제
            reviewLikeRepository.delete(list);
            reviewRepository.likeDeleteByReviewNo(reviewNo);
        }
    }


}
