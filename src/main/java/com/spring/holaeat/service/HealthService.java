package com.spring.holaeat.service;

import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.health.HealthRepository;
import com.spring.holaeat.domain.health.HealthRequestDto;
import com.spring.holaeat.domain.photo.Photo;
import com.spring.holaeat.domain.photo.PhotoRepository;
import com.spring.holaeat.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HealthService {

    private final HealthRepository healthRepository;
    private final PhotoRepository photoRepository;
    private final FileHandler fileHandler;

    //전체 조회
//    public List<Health> findAllByOrderByHealthNoDesc(){
//        List<Health> list= healthRepository.findAllByOrderByHealthNoDesc();
//        return list;
//    }

    public Health getHealthByHealthNo(long healthNo){
        Health health=healthRepository.findById(healthNo).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        return health;

    }

    //수정
    public void updateHealth(Health health, HealthRequestDto healthDto){
        health.update(healthDto);
        healthRepository.save(health);
    }


    //이미지 수정시
    @Transactional
    public void remainImage(Health health, byte[] file){
        health.remainFile(file);
        healthRepository.save(health);
    }

    //삭제
    @Transactional
    public void healthBoardDelete(long healthNo){
        //게시글 삭제
        healthRepository.deleteById(healthNo);
    }

    //이미지 여러개
//    @Transactional
//    public Long create(
//            HealthRequestDto healthDto,
//            List<MultipartFile> files
//    ) throws Exception {
//        // 파일 처리를 위한 health 객체 생성
//        Health health = new Health(healthDto);
//
//        List<Photo> photoList = fileHandler.parseFileInfo(files);
//
//        // 파일이 존재할 때에만 처리
//        if(!photoList.isEmpty()) {
//            for(Photo photo : photoList) {
//                // 파일을 DB에 저장
//                health.addPhoto(photoRepository.save(photo));
//            }
//        }
//
//        return healthRepository.save(health).getHealthNo();
//    }



















}
