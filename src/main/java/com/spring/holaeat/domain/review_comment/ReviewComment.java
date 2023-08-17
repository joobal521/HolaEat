package com.spring.holaeat.domain.review_comment;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRequestDto;
import com.spring.holaeat.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;


@NoArgsConstructor
@Getter
@Table(name="review_comment")
@Entity
public class ReviewComment extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @JoinColumn(name = "reviewNo", nullable = false)
    private long reviewNo;

    @JoinColumn(name = "userId", nullable = false)
    private String userId;

    @Column(length = 255)
    private String content;

    //추가
//    @ManyToOne
//    @JoinColumn(name = "review_no")
//    private Review review;



    public ReviewComment(ReviewCommentRequestDto reviewCommentRequestDto){

        this.commentId = reviewCommentRequestDto.getCommentId();
        this.reviewNo = reviewCommentRequestDto.getReviewNo();
        this.userId = reviewCommentRequestDto.getUserId();
        this.content = reviewCommentRequestDto.getContent();
    }


    public void update(ReviewCommentRequestDto reviewCommentRequestDto) {
        this.content = reviewCommentRequestDto.getContent();
    }


}
