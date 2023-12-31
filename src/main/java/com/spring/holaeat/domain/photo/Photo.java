package com.spring.holaeat.domain.photo;

import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.util.Timestamp;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name="photo")
public class Photo extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    //다대일
    @ManyToOne
    @JoinColumn(name="health_no")
    private Health health;

    @Column(nullable = false)
    private String fileName; //파일 원본명

    @Column(nullable = false)
    private String filePath; //파일 저장 경로

    private Long fileSize;


    @Builder
    public Photo(String fileName, String filePath, Long fileSize){
        this.fileName=fileName;
        this.filePath=filePath;
        this.fileSize=fileSize;
    }

    //health정보 저장
//    public void setHealth(Health health){
//        this.health=health;

        //게시글에 현재 파일이 존재하지 않는다면
//        if(!health.getPhoto().contains(this)){
            //파일 추가
//            health.getPhoto().add(this);
//        }
//    }
    }






