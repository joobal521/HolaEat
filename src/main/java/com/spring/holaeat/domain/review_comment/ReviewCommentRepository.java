package com.spring.holaeat.domain.review_comment;

import com.spring.holaeat.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    public List<ReviewComment> findAll();


    public List<ReviewComment> findAllByReviewNo(long reviewNo);

    public void deleteByReviewNo(long reviewNo);

//    public List<ReviewComment> findAllByCommentId(long commentId);

}
