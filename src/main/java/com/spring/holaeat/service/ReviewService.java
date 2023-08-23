package com.spring.holaeat.service;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;


    //부모 테이블 삭제전 자식 테이블 삭제
    @Transactional
    public void deleteReviewsByUserId(String userId) {
        reviewRepository.deleteByUserId(userId);
    }
    //검색

   public List<Review> getAllReview(){
       return reviewRepository.findAll();

   }

    //페이징처리추가(8/14)
    public Page<Review> getReviewList(Pageable pageable){
        return reviewRepository.findAll(pageable);
    }



    //전체 적용
    public List<Review> findAllByOrderByReviewNoDesc(Pageable adjustedPageable){
        List<Review> list = reviewRepository.findAllByOrderByReviewNoDesc(adjustedPageable);
//        System.out.println("list : " + list);
        return list;
    }

    public Review findByReviewNo(long reviewNo){
        Review review = reviewRepository.findById(reviewNo).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 리뷰입니다.")
        );
       return review;
    }

    public List<Review> getReviewsByPage(int page, int reviewsPerPage) {
        Pageable pageable = PageRequest.of(page, reviewsPerPage);
        return reviewRepository.findAllByOrderByReviewNoDesc(pageable);
    }
    //수정
    @Transactional
    public void update(Review review, ReviewRequestDto reviewRequestDto){

        review.update(reviewRequestDto);
        reviewRepository.save(review);
    }

    @Transactional
    public void remainImage(Review review,byte[] img){
        review.remainImg(img);
        reviewRepository.save(review);
    }

        //삭제
    @Transactional
public void delete(long reviewNo){
        reviewRepository.deleteById(reviewNo);


}


//좋아요 기능 repository > service
    public void insert(long reviewNo){

        reviewRepository.likeCountByReviewNo(reviewNo);
        //기존 값 가져오기

    }







}
