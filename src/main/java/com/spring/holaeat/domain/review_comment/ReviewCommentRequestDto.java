package com.spring.holaeat.domain.review_comment;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ReviewCommentRequestDto {

    private String commentId;
    private long reviewNo;
    private String userId;
    private String content;
    private int like;

}
