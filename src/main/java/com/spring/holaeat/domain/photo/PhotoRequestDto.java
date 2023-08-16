package com.spring.holaeat.domain.photo;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PhotoRequestDto {
    //private Long fileId;
    //private HealthBoard healthBoard;
    private String fileName;
    private Long fileSize;
    private String filePath;

    @Builder
    public PhotoRequestDto(String fileName, String filePath, Long fileSize){
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

}
