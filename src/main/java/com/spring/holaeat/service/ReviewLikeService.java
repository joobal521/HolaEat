//package com.spring.holaeat.service;
//
//import com.spring.holaeat.domain.review.Review;
//import com.spring.holaeat.domain.review.ReviewRepository;
//import com.spring.holaeat.domain.review_like.ReviewLike;
//import com.spring.holaeat.domain.review_like.ReviewLikeRepository;
//import com.spring.holaeat.domain.review_like.ReviewLikeRequestDto;
//import com.spring.holaeat.domain.user.User;
//import com.spring.holaeat.domain.user.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//
//@Service
//@RequiredArgsConstructor
//public class ReviewLikeService {
//
//    private final ReviewLikeRepository reviewlikeRepository;
//    private final UserRepository userRepository;
//    private final ReviewRepository reviewRepository;
//
//    @Transactional
//    public ResponseEntity<ReviewLikeRequestDto>likeUp(ReviewLikeRequestDto reviewLikeRequestDto) throws Exception {
//
//        User userId = userRepository.findById(String.valueOf(reviewLikeRequestDto.getUserId()))
//                .orElseThrow(ChangeSetPersister.NotFoundException::new);
//
//        Review reviewNo = reviewRepository.findById(reviewLikeRequestDto.getReviewNo())
//                .orElseThrow(ChangeSetPersister.NotFoundException::new);
//
//        // 이미 좋아요되어있으면 에러 반환
//        if (reviewlikeRepository.findByUserIdAndReviewNo(userId, reviewNo).set()) {
//            //TODO 409에러로 변경
//            throw new Exception();
//        }
//
//        ReviewLike reviewLike = ReviewLike.builder()
//                .reviewNo(reviewNo)
//                .userId(userId)
//                .build();
//
//        reviewlikeRepository.save(reviewLike);
//        reviewRepository.updateCount(reviewNo, true);
//    }
//
//    @Transactional
//    public void likeDown(ReviewLikeRequestDto reviewLikeRequestDTO) {
//
//        User userId = userRepository.findById(reviewLikeRequestDTO.getUserId())
//                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Could not found user id : " + reviewLikeRequestDTO.getUserId()));
//
//        Review reviewNo = reviewRepository.findById(reviewLikeRequestDTO.getReviewNo())
//                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Could not found review no : " + reviewLikeRequestDTO.getReviewNo()));
//
//        ReviewLike reviewLike = reviewlikeRepository.findByUserIdAndreviewNo(userId, reviewNo)
//                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Could not found reviewLikeNo id"));
//
//        reviewlikeRepository.delete(reviewLike);
//
//        reviewRepository.updateCount(reviewNo, false);
//    }
//}
//
//
