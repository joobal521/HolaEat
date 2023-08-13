package com.spring.holaeat.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    public List<Review> findAllByOrderByReviewNoDesc();

    //페이징
    public List<Review> findAllByTitleLike(String pattern, Pageable pageable);

    public List<Review> findAll();

    //페이징으로 추가1 - 데이터를조회하는 메서드 정의 제목 검색해서 내림차순 정렬
//    public Page<Review> findAllByTitleLikeOrderByReviewNoDesc(String pattern, Pageable pageable);

//    Page<Review> findByTitleContaining(String searchKeyword, Pageable pageable);


}
