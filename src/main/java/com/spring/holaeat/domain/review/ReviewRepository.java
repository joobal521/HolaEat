package com.spring.holaeat.domain.review;

import com.spring.holaeat.domain.ingredients.Ingredients;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAllByOrderByReviewNoDesc(Pageable adjustedPageable);

    //페이징
    public List<Review> findAllByTitleLike(String pattern, Pageable pageable);
    public List<Review> findAllByUserIdLike(String pattern, Pageable pageable);



    @Query(nativeQuery = true, value = "SELECT * FROM review WHERE title LIKE %?1% OR user_id LIKE %?1%")
    public List<Review> findAllByTitleLikeOrUserIdLike(String pattern,Pageable pageable);

    public List<Review> findAll();


}