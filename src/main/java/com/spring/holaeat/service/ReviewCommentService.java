package com.spring.holaeat.service;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review_comment.ReviewComment;
import com.spring.holaeat.domain.review_comment.ReviewCommentRepository;
import com.spring.holaeat.domain.review_comment.ReviewCommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewCommentService {

//수정
    private final ReviewCommentRepository reviewCommentRepository;
    @Transactional
    public void update(ReviewComment reviewComment, ReviewCommentRequestDto reviewCommentRequestDto) {
        reviewComment.update(reviewCommentRequestDto);
        reviewCommentRepository.save(reviewComment);
    }

//수정(8/21)
//    @Transactional
//    public void update(Long postsId, Long id, reviewCommentDto.Request dto) {
//        Comment comment = reviewCommentRepository.findByPostsIdAndId(postsId, id).orElseThrow(() ->
//                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));
//
//        comment.update(dto.getComment());
//    }



    //삭제
    @Transactional
    public void delete(long commentId){
        System.out.println("서비스 commentID" +commentId );
        reviewCommentRepository.deleteById(commentId);


    }

    //댓글있는 게시글 삭제
    @Transactional
    public void deleteByReviewNo(long reviewNo){
        reviewCommentRepository.deleteByReviewNo(reviewNo);

    }


    //부모 테이블 삭제전 자식 테이블 삭제
   @Transactional
    public void deleteReviewCommentByUserId(String userId) {
        reviewCommentRepository.deleteByUserId(userId);
    }

    public List<ReviewComment> findAllByUserId(String userId){
        return reviewCommentRepository.findAllByUserId(userId);
    }


//    public List<Comment> getCommentsByReviewNo(String reviewNo) {
//        return commentList;
//    }
//    public void addComment(Comment comment) {
//    }
//



//    private final ReviewCommentRepository reviewCommentRepository;
//    //수정
//    @Transactional
//    public void update(ReviewComment reviewComment, ReviewCommentRequestDto reviewCommentRequestDto){
//
//        reviewComment.update( reviewCommentRequestDto);
//        reviewCommentRepository.save(reviewComment);
//    }
}
