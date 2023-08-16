package com.spring.holaeat.domain.review_comment;

import com.spring.holaeat.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, String> {

    public List<ReviewComment> findAll();

}
