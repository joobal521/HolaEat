package com.spring.holaeat.domain.health;

import lombok.Getter;

@Getter
public class HealthListResponseDto {
    private Long healthNo;
    private String id;
    private String title;
    private Long thumbnailId;  // 썸네일 id



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
