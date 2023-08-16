package com.spring.holaeat.service;

import com.spring.holaeat.domain.photo.Photo;
import com.spring.holaeat.domain.photo.PhotoRepository;
import com.spring.holaeat.domain.photo.PhotoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PhotoService {

    private final PhotoRepository photoRepository;


    //  이미지 개별 조회
//    @Transactional(readOnly = true)
//    public PhotoRequestDto findByFileId(Long fileId){
//
//        Photo entity = photoRepository.findById(fileId).orElseThrow(()
//                -> new IllegalArgumentException("해당 파일이 존재하지 않습니다."));
//
//        PhotoRequestDto photoDto = PhotoRequestDto.builder()
//                .fileName(entity.getFileName())
//                .filePath(entity.getFilePath())
//                .fileSize(entity.getFileSize())
//                .build();
//
//        return photoDto;
//    }
//
//
//    //이미지 전체 조회
//    @Transactional(readOnly = true)
//    public List<PhotoResponseDto> findAllByBoard(Long boardId){
//
//        List<Photo> photoList = photoRepository.findAllByBoardId(boardId);
//
//        return photoList.stream()
//                .map(PhotoResponseDto::new)
//                .collect(Collectors.toList());
//    }
}
