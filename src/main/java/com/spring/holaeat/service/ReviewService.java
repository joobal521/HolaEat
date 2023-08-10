package com.spring.holaeat.service;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    //전체 적용
    public List<Review> findAllByOrderByReviewNoDesc(){
        List<Review> list = reviewRepository.findAllByOrderByReviewNoDesc();
        System.out.println("list : " + list);
        return list;
    }

    public List<Review> findByReviewNo(long reviewNo){
        List<Review> list = reviewRepository.findByReviewNo(reviewNo);
        System.out.println("reviewNo : " + reviewNo);
        System.out.println("list" + list.get(0).getUserId());
       return list;
    }



    //수정
    @Transactional
    public void update(Review review, ReviewRequestDto reviewRequestDto){

        review.update(reviewRequestDto);
        reviewRepository.save(review);
    }


        //삭제
    @Transactional
public void delete(long reviewNo){
        reviewRepository.deleteById(reviewNo);


}










}
