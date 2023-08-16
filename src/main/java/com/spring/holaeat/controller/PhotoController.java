package com.spring.holaeat.controller;


import com.spring.holaeat.domain.health.HealthRequestDto;
import com.spring.holaeat.service.HealthService;
import com.spring.holaeat.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PhotoController {

    private final HealthService healthService;
    private final PhotoService photoService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(
            @RequestPart(value="image", required=false) List<MultipartFile> files,
            @RequestPart(value = "healthBoardDto") HealthRequestDto healthDto
    ) throws Exception {

        return healthService.create(healthDto, files);
    }


//    //썸네일 용 이미지
//    @CrossOrigin
//    @GetMapping(
//            value = "/thumbnail/{id}",
//            // 출력하고자 하는 데이터 포맷 정의
//            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
//    )
//    public ResponseEntity<byte[]> getThumbnail(@PathVariable Long id) throws IOException {
//
//        // 이미지가 저장된 절대 경로 추출
//        String absolutePath =
//                new File("").getAbsolutePath() + File.separator + File.separator;
//        String path;
//
//
//        if(id != 0) {  // 전달되어 온 이미지가 기본 썸네일이 아닐 경우
//            Photo photoDto = photoService.
//            path = photoDto.getFilePath();
//        }
//        else {  // 전달되어 온 이미지가 기본 썸네일일 경우
//            path = "images" + File.separator + "thumbnail" + File.separator + "thumbnail.png";
//        }
//
//        // FileInputstream의 객체를 생성하여
//        // 이미지가 저장된 경로를 byte[] 형태의 값으로 encoding
//        InputStream imageStream = new FileInputStream(absolutePath + path);
//        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
//        imageStream.close();
//
//        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
//    }




}
