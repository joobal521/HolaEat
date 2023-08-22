package com.spring.holaeat.domain.review;

import com.spring.holaeat.domain.review_comment.ReviewComment;
import com.spring.holaeat.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;


@NoArgsConstructor
@Getter
@Table(name = "review")
@Entity
public class Review extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewNo;

    //foreign key (user_id) references user(user_id)
//    @ManyToOne(fetch = FetchType.LAZY)  // 다대일 관계, 필요에 따라 EAGER로 설정 가능
    @JoinColumn(name = "userId", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(length = 4000)
    private String content;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;

    @ColumnDefault("0")
    @Column
    private int reviewLike;

//추가
//    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ReviewComment> comments;


    public Review(ReviewRequestDto reviewRequestDto) {
        this.title = reviewRequestDto.getTitle();
        this.content = reviewRequestDto.getContent();
        this.userId = reviewRequestDto.getUserId();
//        this.reviewLike = reviewRequestDto.getReviewLike();

        if (reviewRequestDto.getImg() != null) {
            try {
                this.img = reviewRequestDto.getImg().getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // 게시글 수정시 기존 이미지 담기위함
    public void remainImg(byte[] img) {
        this.img = img;
    }


    public void update(ReviewRequestDto reviewRequestDto) {
        this.title = reviewRequestDto.getTitle();
        this.content = reviewRequestDto.getContent();

        if (reviewRequestDto.getImg() != null) {
            try {
                this.img = reviewRequestDto.getImg().getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.img = null;
        }
    }


}
