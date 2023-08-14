package com.spring.holaeat.domain.health_img;


import com.spring.holaeat.domain.health_board.HealthBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class HealthImgRequestDto {
    private Long fileId;
    //private HealthBoard healthBoard;
    private String fileName;
    private Long fileSize;
    private String filePath;

}
