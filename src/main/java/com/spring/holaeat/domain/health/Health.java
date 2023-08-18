package com.spring.holaeat.domain.health;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.photo.Photo;
import com.spring.holaeat.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name="health")
public class Health extends Timestamp {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long healthNo;

    @Column(nullable = false)
    private String title;

    @Column(length = 4000)
    private String content;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] file;





    //다중 이미지 업로드

//    @OneToMany(
//            mappedBy = "health",
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            orphanRemoval = true
//    )
//
//    private List<Photo> photo= new ArrayList<>();




    public Health(HealthRequestDto healthDto){
        this.title=healthDto.getTitle();
        this.content=healthDto.getContent();

        if (healthDto.getFile() != null) {
            try {
                this.file = healthDto.getFile().getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

 }

//    @Builder
//    public HealthBoard(Admin admin, String title, String content) {
//        this.admin = admin;
//        this.title = title;
//        this.content = content;
//    }

public void update(String title, String content){
     this.title=title;
     this.content=content;
}

//healthBoard에서 파일 처리 위함
//    public void addPhoto(Photo photo){
//     this.photo.add(photo);
//     //게시글에 파일이 저장 되어 있지 않은 경우
//        if(photo.getHealth()!=this) {
//            //파일 저장
//            photo.setHealth(this);
//        }
//
//    }



}
