package com.spring.holaeat.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAllByOrderByReviewNoDesc();

    //페이징
    public List<Review> findAllByTitleLike(String pattern, Pageable pageable);

    public List<Review> findAll();


}