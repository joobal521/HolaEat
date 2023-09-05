package com.spring.holaeat.domain.review_comment;

import com.spring.holaeat.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    public List<ReviewComment> findAll();


    public List<ReviewComment> findAllByReviewNo(long reviewNo);

    public void deleteByReviewNo(long reviewNo);

    // 해당 userId와 관련된 리뷰 레코드를 삭제하는 메서드 정의
    public void deleteByUserId(String userId);

    List<ReviewComment> findAllByUserId(String userId);

//    public List<ReviewComment> findAllByCommentId(long commentId);

}