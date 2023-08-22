package com.spring.holaeat.domain.review_like;

import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


@Setter
@Getter
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


    // Public 생성자 추가
    public ReviewLike() {
    }

    public void setReviewNo(long reviewNo) {
        this.reviewNo = reviewNo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}