package com.spring.holaeat.domain.review_like;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {



//    public List<ReviewLike> findByUserIdAndReviewNo(String userId, long reviewNo);
    public ReviewLike findByUserIdAndReviewNo(String userId, long reviewNo);

    //엔티티 삭제
    public void deleteByUserIdAndReviewNo(String userId, long reviewNo);

//엔티티 추가
    ReviewLike save(ReviewLike reviewLike);

   // ReviewLike findByUserIdAndReviewNo(String userId, Long reviewNo);


    //좋아요 있는 게시글 삭제
    public void deleteByReviewNo(long reviewNo);



}
