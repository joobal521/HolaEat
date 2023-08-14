package com.spring.holaeat.domain.health_img;

import com.spring.holaeat.domain.health_board.HealthBoard;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name="health_img")
public class HealthImg  {

    @Id
    private Long fileId;

    //다대일
    @ManyToOne
    @JoinColumn(name="health_no")
    private HealthBoard healthBoard;

    @Column(nullable = false)
    private String fileName; //파일 원본명

    private Long fileSize;

    @Column(nullable = false)
    private String filePath; //파일 저장 경로


    @Builder
    public HealthImg(String fileName, String filePath, Long fileSize){
        this.fileName=fileName;
        this.filePath=filePath;
        this.fileSize=fileSize;
    }

    //healthBoard정보 저장
    public void setHealthBoard(HealthBoard healthBoard){
        this.healthBoard=healthBoard;

        //게시글에 현재 파일이 존재하지 않는다면
        if(!healthBoard.getHealthImg().contains(this)){
            //파일 추가
            healthBoard.getHealthImg().add(this);
        }
    }





}
