package com.spring.holaeat.domain.health;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.photo.Photo;
import com.spring.holaeat.util.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "health")
public class Health extends Timestamp {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_no")
    private Long healthNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private Admin admin; //관리자

    @Column(nullable = false)
    private String title;  //제목

    @Column(length = 4000)
    private String content; //내용

    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;


    //다중 이미지 업로드
//    @OneToMany(
//            mappedBy = "health",
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            orphanRemoval = true
//    )
//    private List<Photo> photo= new ArrayList<>();

//    @Builder
//    public Health(String id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

    //public Health(HealthRequestDto healthDto){
    //this.admin=healthDto.getAdmin();
    //    this.title=healthDto.getTitle();
    //    this.content=healthDto.getContent();
    // }

    //  public void update(HealthRequestDto healthDto){
    //      this.title=healthDto.getTitle();
    //      this.content=healthDto.getContent();
    //  }

    //healthBoard에서 파일 처리 위함
    //   public void addPhoto(Photo photo){
    //       this.photo.add(photo);
    //       //게시글에 파일이 저장 되어 있지 않은 경우
    //       if(photo.getHealth()!=this) {
    //파일 저장
    //           photo.setHealth(this);
    //       }

    //   }


    public Health(HealthRequestDto healthDto) {
        this.title = healthDto.getTitle();
        this.content = healthDto.getContent();

        if (healthDto.getImg() != null) {
            try {
                this.img = healthDto.getImg().getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //게시글 수정시 기본 이미지 담기
    public void remainImg(byte[] img) {
        this.img = img;
    }


    //수정
    public void update(HealthRequestDto healthDto) {
        this.title = healthDto.getTitle();
        this.content = healthDto.getContent();
        if (healthDto.getImg() != null) {
            try {
                this.img = healthDto.getImg().getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.img = null;
        }
    }


}
