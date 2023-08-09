package com.spring.holaeat.domain.review;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    //public List<Board> findByNo(long reviewNo);

    public List<Review> findAllByOrderByReviewNoDesc();

    public List<Review> findAllByTitleLike(String pattern, Pageable pageable);
}
