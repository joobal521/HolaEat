package com.spring.holaeat.service;

import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.health.HealthRepository;
import com.spring.holaeat.domain.health.HealthRequestDto;
import com.spring.holaeat.domain.photo.Photo;
import com.spring.holaeat.domain.photo.PhotoRepository;
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
    public List<Health> findAllByOrderByHealthNoDesc(){
        List<Health> list= healthRepository.findAllByOrderByHealthNoDesc();
        return list;
    }

    public Health getHealthBoardByHealthNo(long healthNo){
        Health health=healthRepository.findById(healthNo).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        return health;

    }
    @Transactional
    public Long create(
            HealthRequestDto healthDto,
            List<MultipartFile> files
    ) throws Exception {
        // 파일 처리를 위한 Board 객체 생성
        Health health = new Health(healthDto);

        List<Photo> photoList = fileHandler.parseFileInfo(files);

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()) {
            for(Photo photo : photoList) {
                // 파일을 DB에 저장
                health.addPhoto(photoRepository.save(photo));
            }
        }

        return healthRepository.save(health).getHealthNo();
    }



    //삭제
    @Transactional
    public void healthBoardDelete(long healthNo){
        //게시글 삭제
        healthRepository.deleteById(healthNo);
    }











}