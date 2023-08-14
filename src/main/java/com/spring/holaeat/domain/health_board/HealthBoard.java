package com.spring.holaeat.domain.health_board;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.health_img.HealthImg;
import com.spring.holaeat.util.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name="health_board")
public class HealthBoard extends Timestamp {


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
            mappedBy = "health_board",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )

    private List<HealthImg> healthImg= new ArrayList<>();




    public HealthBoard(HealthBoardRequestDto healthBoardDto){
     this.admin=healthBoardDto.getAdmin();
     this.title=healthBoardDto.getTitle();
     this.content=healthBoardDto.getContent();
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
    public void addHealthImg(HealthImg healthImg){
     this.healthImg.add(healthImg);
     //게시글에 파일이 저장 되어 있지 않은 경우
        if(healthImg.getHealthBoard()!=this) {
            //파일 저장
            healthImg.setHealthBoard(this);
        }

    }



}
