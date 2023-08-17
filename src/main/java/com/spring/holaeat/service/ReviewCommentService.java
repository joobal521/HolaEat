package com.spring.holaeat.service;

import com.spring.holaeat.domain.review_comment.ReviewComment;
import com.spring.holaeat.domain.review_comment.ReviewCommentRepository;
import com.spring.holaeat.domain.review_comment.ReviewCommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    //삭제
    @Transactional
    public void delete(long commentId){
        reviewCommentRepository.deleteById(commentId);


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
