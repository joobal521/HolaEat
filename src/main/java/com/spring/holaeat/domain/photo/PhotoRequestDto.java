package com.spring.holaeat.domain.photo;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PhotoRequestDto {
    private Long fileId;
    //private HealthBoard healthBoard;
    private String fileName;
    private Long fileSize;
    private String filePath;

}
