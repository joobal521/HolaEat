package com.spring.holaeat.domain.review_like;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewLikeRequestDto {

    private Long userId;
    private Long reviewNo;

    public ReviewLikeRequestDto(Long userId, Long reviewNo) {
        this.userId = userId;
        this.reviewNo = reviewNo;
    }
}

