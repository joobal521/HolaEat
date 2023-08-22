package com.spring.holaeat.domain.review_like;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewLikeRepository extends JpaRepository<Review, Long> {




//    public List<Review> findByUserIdAndReviewNo(User userId, Review reviewNo);
//
//    public void updateCount(Review review, boolean check);
}
