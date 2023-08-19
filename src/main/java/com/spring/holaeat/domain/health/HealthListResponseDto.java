package com.spring.holaeat.domain.health;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
public class HealthListResponseDto {
    private Long healthNo;
    //private String id;
    private String title;
    private MultipartFile file;
    //private Long thumbnailId;  // 썸네일 id


//    public HealthListResponseDto(Health entity){
//        this.healthNo=entity.getHealthNo();
//        this.title=entity.getTitle();
//        if(entity.getFile() !=null) {
//
//            try {
//                this.file = entity.getFile()
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }


//    public HealthListResponseDto(Health entity) {
//        this.healthNo = entity.getHealthNo();
//        this.id = entity.getId();
//        this.title = entity.getTitle();
//
//        if(!entity.getPhoto().isEmpty())  // 첨부파일 존재 o
//            this.thumbnailId = entity.getPhoto().get(0).getFileId();  // 첫번째 이미지 반환
//        else // 첨부파일 존재 x
//            this.thumbnailId = 0L;  // 서버에 저장된 기본 이미지 반환
//    }

}
