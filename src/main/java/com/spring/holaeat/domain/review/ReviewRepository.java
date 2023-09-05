package com.spring.holaeat.domain.review;

import com.spring.holaeat.domain.ingredients.Ingredients;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAllByOrderByReviewNoDesc(Pageable adjustedPageable);

    //페이징
    public List<Review> findAllByTitleLike(String pattern, Pageable pageable);
    public List<Review> findAllByUserIdLike(String pattern, Pageable pageable);



    @Query(nativeQuery = true, value = "SELECT * FROM review WHERE title LIKE %?1% OR user_id LIKE %?1%")
    public List<Review> findAllByTitleLikeOrUserIdLike(String pattern,Pageable pageable);

    public List<Review> findAll();

    // 해당 userId와 관련된 리뷰 레코드를 삭제하는 메서드 정의
   public void deleteByUserId(String userId);

   //유저로 조회(내가 쓴 글)
     List<Review> findAllByUserId(String userId);


   //좋아요 기능
   @Modifying
   @Transactional
    @Query(nativeQuery = true,value = "UPDATE review SET review_like = review_like + 1 WHERE review_no = ?1")
    public void likeCountByReviewNo(long reviewNo);

    //좋아요 취소
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE review SET review_like = review_like - 1 WHERE review_no = ?1")
    public void likeDeleteByReviewNo(long reviewNo);

    //게시글 조회수 출력
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE review SET review_count = review_count + 1 WHERE review_no = ?1")
    public void reviewCountByReviewNo(long reviewNo);




}