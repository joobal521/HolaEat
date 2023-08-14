package com.spring.holaeat.domain.health_board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class HealthBoardFileVo {
    private String id;
    private String title;
    private String content;
    private List<MultipartFile> file;

}
