package com.spring.holaeat.domain.review_like;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.user.User;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "review_like")
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_like_no")
    private Long reviewLikeNo;

//    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private String userId;

//    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_no")
    private Long reviewNo;

    public ReviewLike(long reviewNo, String userId) {
        this.reviewNo = reviewNo;
        this.userId = userId;
    }

    public void setReviewNo(long reviewNo) {
        this.reviewNo = reviewNo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}