package com.spring.holaeat.domain.review;

import com.spring.holaeat.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;


@NoArgsConstructor
@Getter
@Table(name="review")
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



    public Review(ReviewRequestDto reviewRequestDto){
        this.title = reviewRequestDto.getTitle();
        this.content = reviewRequestDto.getContent();
        this.userId = reviewRequestDto.getUserId();

        if (reviewRequestDto.getImg() != null) {
            try {
                this.img = reviewRequestDto.getImg().getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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
        }else{
            this.img=null;
        }
    }


}
