package com.spring.holaeat.domain.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ReviewResponseDto {
    private long reviewNo;
    private String userId;
    private String title;
    private String content;
    private byte[] img;
    private int reviewLike;
    private int likedStatus;

    public static List<ReviewResponseDto> parse(List<Review> list) {
        List<ReviewResponseDto> result = new ArrayList<>();

        for(Review review : list) {
            ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
            reviewResponseDto.setReviewNo(review.getReviewNo());
            reviewResponseDto.setUserId(review.getUserId());
            reviewResponseDto.setTitle(review.getTitle());
            reviewResponseDto.setContent(review.getContent());
            reviewResponseDto.setReviewLike(review.getReviewLike());

            result.add(reviewResponseDto);
        }

        return result;
    }
}
