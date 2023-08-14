package com.spring.holaeat.domain.health;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.photo.Photo;
import com.spring.holaeat.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name="health")
public class Health extends Timestamp {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long healthNo;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Admin.class)
    @JoinColumn(name = "Id", updatable = false)
    @JsonBackReference
    private Admin admin;

    @Column(nullable = false)
    private String title;

    @Column(length = 4000)
    private String content;

    @OneToMany(
            mappedBy = "health",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )

    private List<Photo> photo= new ArrayList<>();




    public Health(HealthRequestDto healthDto){
     this.admin=healthDto.getAdmin();
     this.title=healthDto.getTitle();
     this.content=healthDto.getContent();
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
    public void addPhoto(Photo photo){
     this.photo.add(photo);
     //게시글에 파일이 저장 되어 있지 않은 경우
        if(photo.getHealth()!=this) {
            //파일 저장
            photo.setHealth(this);
        }

    }



}
